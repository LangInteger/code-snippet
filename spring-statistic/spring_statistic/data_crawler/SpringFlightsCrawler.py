import requests

from spring_statistic.utils import DateUtils, IOUtils
from spring_statistic.utils.const import FlightAndRoutConst


def build_file_name():
    date_time = DateUtils.current_date_time_for_file_name()
    return IOUtils.absolute_path('data/source/dynamic/flights-' + date_time + '.html')


def download_flight_data():
    """
    Download flights from Spring Airline and save to
    {project-root-dir/data/source/dynamic/flights-date_time_string.html} file
    :return: file saving path
    """
    response = requests.get(FlightAndRoutConst.URL_FLIGHTS_OF_SPRING_AIRLINE,
                            headers={'Content-type': 'text/html; charset=utf-8'})
    output_path = build_file_name()
    # Using response.text instead of response.content for chinese string display
    IOUtils.write_to_file(str(response.text), output_path)
    return output_path
