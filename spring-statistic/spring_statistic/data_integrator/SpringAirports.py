"""
Statistic of airports Spring Airline reaches
"""


import json

from spring_statistic.utils import IOUtils
from spring_statistic.utils.const import CountryConst, AirportConst


def format_airport(original_airport):
    return {AirportConst.FIELD_CITY_NAME: original_airport[1][1], AirportConst.FIELD_AIRPORT_CODE: original_airport[1][3]}


def filter_chinese_airport(original_airport):
    return CountryConst.COUNTRY_NAME_CN_CHINA in original_airport[1]


def get_all_airports_tuple_original(file):
    with open(file, encoding='utf-8') as readFile:
        airports = json.load(readFile)
    return airports.items()


def get_all_airports_formatted(file):
    original_airport_tuples = get_all_airports_tuple_original(file)
    return list(map(lambda original_airport: format_airport(original_airport), original_airport_tuples))


def get_chinese_airports_formatted(file):
    original_airport_tuples = get_all_airports_tuple_original(file)
    origin_chinese_airport_tuples = list(filter(lambda airport: filter_chinese_airport(airport), original_airport_tuples))
    return list(map(lambda original_airport: format_airport(original_airport), origin_chinese_airport_tuples))


def print_chinese_airports_formatted(file):
    print(json.dumps(get_chinese_airports_formatted(file), ensure_ascii=False, indent=4, sort_keys=True))


def write_chinese_airports_formatted(input_file, output_file):
    """
    Get all airports in china mainLand that Spring Airline reaches, and write it to file
    :param input_file: the original information from Spring Airline
    :param output_file: the target file write result to
    """
    chinese_airports = get_chinese_airports_formatted(input_file)
    IOUtils.write_as_json_to_file(chinese_airports, output_file)


def get_chinese_airports_mapped_by_name():
    """
    Get all airports in china that Spring Airline reaches, resulting in a dict with city name as key
    and airport dict itself as value
    :return: dictionary chinese airports that Spring Airline reaches
    """
    airports = get_chinese_airports_formatted(IOUtils.absolute_path(AirportConst.PATH_AIRPORTS_FILE))
    return {airport[AirportConst.FIELD_CITY_NAME]: airport for airport in airports}

