#!/usr/bin/env python3

import logging

logging.basicConfig(format='%(asctime)s:%(levelname)s:%(filename)s:%(lineno)s:%(funcName)2s(): %(message)s', level=logging.DEBUG)
log = logging.getLogger()
import sys
import time
#logging.basicConfig( encoding='utf-8', level=logging.DEBUG)
def my_function():
  print("Hello from a function")
  logging.debug('This message should go to the log file')
  log.debug('This message should go to the log file')
my_function()
