__author__ = 'wangjun'

import httplib

# httpClient = httplib.HTTPSConnection("www.baidu.com")
httpClient = httplib.HTTPConnection("www.baidu.com", 80, timeout=10)
httpClient.request("GET", "")
response = httpClient.getresponse()
result = response.read()
httpClient.close()
print result