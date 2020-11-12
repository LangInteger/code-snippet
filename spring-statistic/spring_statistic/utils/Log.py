import logging

# create a file handler
import os

from spring_statistic.utils import DateUtils, IOUtils


if not os.path.exists(IOUtils.absolute_path('logs')):
    os.makedirs(IOUtils.absolute_path('logs'))

date = DateUtils.current_date()
fileHandler = logging.FileHandler(IOUtils.absolute_path(f'logs/Business-{date}.log'))
fileHandler.setLevel(logging.INFO)

# create a logging format
formatter = logging.Formatter('%(asctime)s - %(thread)d - %(name)s - %(levelname)s - %(message)s')
fileHandler.setFormatter(formatter)


consoleHandler = logging.StreamHandler()
consoleHandler.setFormatter(formatter)
