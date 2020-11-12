"""
Statistic of hot airports Spring Airline reaches.
The data source of hot airports is different from airports, so make the analysis separated.
"""


import json

from spring_statistic.utils import IOUtils
from spring_statistic.utils.const import AirportConst


def format_airport(original_airport):
    return {AirportConst.FIELD_CITY_NAME: original_airport[AirportConst.FIELD_HOT_AIRPORT_CITY_NAME_ORIGINAL],
            AirportConst.FIELD_AIRPORT_CODE: original_airport[AirportConst.FIELD_HOT_AIRPORT_CITY_CODE_ORIGINAL]}


def get_hot_chinese_airports_original(file):
    with open(file, encoding='utf-8') as readFile:
        airports = json.load(readFile)
    return airports[0]['List']


def get_hot_chinese_airports_formatted(file):
    hot_airports_original = get_hot_chinese_airports_original(file)
    return list(map(lambda original_airport: format_airport(original_airport), hot_airports_original))


def print_chinese_airports_formatted(file):
    print(json.dumps(get_hot_chinese_airports_formatted(file), ensure_ascii=False, indent=4, sort_keys=True))


def write_hot_chinese_airports_formatted(input_file, output_file):
    """
    Get all airports in china mainLand that Spring Airline reaches, and write it to fiel
    :param input_file:  the original information from Spring Airline
    :param output_file: the target file write result to
    """
    hot_chinese_airports = get_hot_chinese_airports_formatted(input_file)
    IOUtils.write_as_json_to_file(hot_chinese_airports, output_file)


def get_hot_airports_mapped_by_code():
    """
    Get all airports in china mainLand that Spring Airline reaches
    :return: A dictionary with airport code as key and the single airport dict itself as value
    """
    airports = get_hot_chinese_airports_formatted(IOUtils.absolute_path(AirportConst.PATH_HOT_AIRPORTS_FILE))
    return {airport[AirportConst.FIELD_AIRPORT_CODE]: airport for airport in airports}
