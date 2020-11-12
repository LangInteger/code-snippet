"""
Date and time related helper functions
"""
import datetime


def current_date_time():
    """
    Get current dateTime as string with format of ISO 8601
    """
    date = datetime.datetime.now()
    return f'{date:%Y-%m-%dT%H:%M:%SZ}'


def current_date_time_for_file_name():
    """
    Get current dateTime as string with format of ISO 8601 almost
    But with some adjustment for file naming
    """
    date = datetime.datetime.now()
    return f'{date:%Y-%m-%dT%H-%M-%SZ}'


def current_date():
    """
    Get current dateTime with yyyy-MM-dd format
    """
    date = datetime.datetime.now()
    return f'{date:%Y-%m-%d}'
