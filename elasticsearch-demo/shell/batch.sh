#!/usr/bin/env bash

curl -is -XPOST '127.0.0.1:9200/_bulk?pretty' -d '
{"delete":{"_index":"test","_type":"users","_id":"AVLYrCaP7RVnGvauu8ir"}}
{"delete":{"_index":"test","_type":"users","_id":"1"}}
'