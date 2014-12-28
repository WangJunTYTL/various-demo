__author__ = 'wangjun'
import logging

logging.debug("debug")
logging.info("info")
logging.warning("debug")
logging.error("error")


import logging.config

logging.config.fileConfig("./logging.conf")
logger = logging.getLogger("example01")

logger.debug('This is debug message')
logger.info('This is info message')
logger.warning('This is warning message')


logger = logging.getLogger("example02")

logger.debug('This is debug message')
logger.info('This is info message')
logger.warning('This is warning message')