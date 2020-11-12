"""
Statistic of flights Spring Airline owns
"""

from bs4 import BeautifulSoup

from spring_statistic.data_integrator import SpringHotAirports, SpringAirports
from spring_statistic.utils import IOUtils, DateUtils
from spring_statistic.utils.const import FlightAndRoutConst, CountryConst, AirportConst


def format_flight(flight_info):
    return {FlightAndRoutConst.FIELD_DEPARTURE_AIRPORT_NAME: flight_info[0],
            FlightAndRoutConst.FIELD_ARRIVAL_AIRPORT_NAME: flight_info[2],
            FlightAndRoutConst.FIELD_FREQUENCY: flight_info[5]}


def filter_chinese_airport(original_airport):
    return CountryConst.COUNTRY_NAME_CN_CHINA in original_airport[1]


def get_all_flights_original(file):
    with open(file, encoding='utf-8') as readFile:
        content = readFile.read()
        soup = BeautifulSoup(content, features='lxml')
        table = soup.find_all('table')[0]
        rows = table.find_all('tr')

        data = []
        for row in rows:
            cols = row.find_all('td')
            cols = [ele.text.strip() for ele in cols]
            data.append([ele for ele in cols if ele])
    return data


def get_all_flights_formatted(file):
    original_flights = get_all_flights_original(file)
    original_flights = list(filter(lambda flight: flight, original_flights))
    return list(map(lambda original_flight: format_flight(original_flight), original_flights))


def get_chinese_flights_formatted(file):
    all_flights = get_all_flights_formatted(file)
    airport_dict = SpringAirports.get_chinese_airports_mapped_by_name()

    chinese_flights = list(filter(
        lambda item: item[FlightAndRoutConst.FIELD_DEPARTURE_AIRPORT_NAME] in airport_dict and
                     item[FlightAndRoutConst.FIELD_ARRIVAL_AIRPORT_NAME] in airport_dict,
        all_flights))

    for flight in chinese_flights:
        departure = airport_dict[flight[FlightAndRoutConst.FIELD_DEPARTURE_AIRPORT_NAME]]
        flight[FlightAndRoutConst.FIELD_DEPARTURE_AIRPORT_CODE] = departure[AirportConst.FIELD_AIRPORT_CODE]
        arrival = airport_dict[flight[FlightAndRoutConst.FIELD_ARRIVAL_AIRPORT_NAME]]
        flight[FlightAndRoutConst.FIELD_ARRIVAL_AIRPORT_CODE] = arrival[AirportConst.FIELD_AIRPORT_CODE]
    return chinese_flights


# For main application - flights
def write_chinese_flights_formatted(input_file, output_file):
    chinese_flights = get_chinese_flights_formatted(input_file)
    IOUtils.write_as_json_to_file(chinese_flights, output_file)


def get_chinese_routes_formatted(input_file):
    flights = get_chinese_flights_formatted(input_file)
    routes = []

    for item in flights:
        del item[FlightAndRoutConst.FIELD_FREQUENCY]
        if item not in routes:
            routes.append(item)

    return routes


def write_chinese_routes_formatted(input_file, output_file):
    """
    Get all flight routes in china mainLand that Spring Airline owns, and write it to file.
    :param input_file:  the original information from Spring Airline
    :param output_file: the target file write result to
    """
    routes = get_chinese_routes_formatted(input_file)
    IOUtils.write_as_json_to_file(routes, output_file)


def write_chinese_routes_formatted_dynamic(input_file):
    """
    Write flights data dynamic
    """
    date_time = DateUtils.current_date_time_for_file_name()
    path = IOUtils.absolute_path('data/result/routes/Flights-' + date_time + '.json')
    write_chinese_routes_formatted(input_file, path)
    return path


def get_hot_chinese_routes_formatted(input_file):
    routes = get_chinese_routes_formatted(input_file)
    hot_airports = SpringHotAirports.get_hot_airports_mapped_by_code()

    return list(filter(lambda route: route[FlightAndRoutConst.FIELD_DEPARTURE_AIRPORT_CODE] in hot_airports or
                                     route[FlightAndRoutConst.FIELD_ARRIVAL_AIRPORT_CODE] in hot_airports, routes))


def write_hot_chinese_routes_formatted(input_file, output_file):
    """
    Get all flight routes in china mainLand that Spring Airline owns, and write it to file
    Hot means the departure or arrival airport is in Spring Airline's hot airports.
    :param input_file:  the original information from Spring Airline
    :param output_file: the target file write result to
    """
    hot_routes = get_hot_chinese_routes_formatted(input_file)
    IOUtils.write_as_json_to_file(hot_routes, output_file)


def write_hot_chinese_routes_formatted_dynamic(input_file):
    """
    Write hot flights data dynamic
    """
    date_time = DateUtils.current_date_time_for_file_name()
    path = IOUtils.absolute_path('data/result/routes/HotFlights-' + date_time + '.json')
    write_hot_chinese_routes_formatted(input_file, path)
    return path

