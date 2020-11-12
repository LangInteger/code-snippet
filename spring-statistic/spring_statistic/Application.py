import logging

from spring_statistic.data_crawler import SpringFlightsCrawler
from spring_statistic.utils import IOUtils, Log
from spring_statistic.utils.const import FlightAndRoutConst, AirportConst
from spring_statistic.data_integrator import SpringHotAirports, SpringFlights, SpringAirports


logger = logging.getLogger(__name__)
logger.addHandler(Log.fileHandler)
logger.addHandler(Log.consoleHandler)
logger.setLevel(logging.INFO)


def do_static_analysis():
    """
    Analysis Spring Airline data based on data already downloaded.
    Mostly it's basement of the dynamic analysis, such as airport and hot airport data.
    Airport data analysis result is for dynamic flight/route data full-filling.
    Hot Airport data analysis result is for dynamic flight/route data filtering.
    """
    # Chinese airports that Spring airline reaches
    logger.info('Airports static analysis start...')
    SpringAirports.write_chinese_airports_formatted(
        IOUtils.absolute_path(AirportConst.PATH_AIRPORTS_FILE),
        IOUtils.absolute_path(AirportConst.PATH_CHINESE_AIRPORTS_OUTPUT))
    logger.info('Airports static analysis complete')

    # Hot chinese airports that Spring airline reaches
    logger.info('Hot airports static analysis start...')
    SpringHotAirports.write_hot_chinese_airports_formatted(
        IOUtils.absolute_path(AirportConst.PATH_HOT_AIRPORTS_FILE),
        IOUtils.absolute_path(AirportConst.PATH_HOT_CHINESE_AIRPORTS_OUTPUT))
    logger.info('Hot airports static analysis complete')

    # Chinese flights that Spring airline owns
    logger.info('Flights static analysis start...')
    SpringFlights.write_chinese_flights_formatted(
        IOUtils.absolute_path(FlightAndRoutConst.PATH_FLIGHTS_FILE),
        IOUtils.absolute_path(FlightAndRoutConst.PATH_FLIGHTS_OUTPUT))
    logger.info('Flights static analysis complete')

    # Chinese routes that Spring airline owns
    logger.info('Routes static analysis start...')
    SpringFlights.write_chinese_routes_formatted(
        IOUtils.absolute_path(FlightAndRoutConst.PATH_FLIGHTS_FILE),
        IOUtils.absolute_path(FlightAndRoutConst.PATH_ROUTES_OUTPUT))
    logger.info('Routes static analysis complete')

    # Hot chinese routes that Spring airline owns
    logger.info('Hot routes static analysis start...')
    SpringFlights.write_hot_chinese_routes_formatted(
        IOUtils.absolute_path(FlightAndRoutConst.PATH_FLIGHTS_FILE),
        IOUtils.absolute_path(FlightAndRoutConst.PATH_HOT_ROUTES_OUTPUT))
    logger.info('Hot routes static analysis complete')


def do_dynamic_analysis():
    """
    Download flights data from Spring Airline and formatted it to route/hot route data
    """
    raw_data = SpringFlightsCrawler.download_flight_data()

    logger.info('Routes dynamic analysis start...')
    SpringFlights.write_chinese_routes_formatted_dynamic(raw_data)
    logger.info('Routes dynamic analysis complete')

    logger.info('Hot routes dynamic analysis start...')
    SpringFlights.write_hot_chinese_routes_formatted_dynamic(raw_data)
    logger.info('Hot routes dynamic analysis complete')


do_static_analysis()
do_dynamic_analysis()

