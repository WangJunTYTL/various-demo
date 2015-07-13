#!/usr/bin/env bash

curl -XDELETE 'http://localhost:9200/_watcher/watch/log_error_watch'
#exit 0

curl -XPUT 'http://localhost:9200/_watcher/watch/log_error_watch' -d '{
  "trigger" : {
    "schedule" : { "interval" : "10s" }
  },
  "input" : {
    "search" : {
      "request" : {
        "indices" : [ "db_order" ],
        "body" : {
          "query" : {
            "match" : { "message": "order_id" }
          }
        }
      }
    }
  },
  "actions": {
    "my_webhook" : {
    "webhook" : {
      "method" : "POST",
      "host" : "127.0.0.1",
      "port" : 9200,
      "path": ":/{{ctx.watch_id}",
      "body" : "{{ctx.watch_id}}:{{ctx.payload.hits.total}}"
    }
  }
    }
  }
}'



curl -XGET 'http://localhost:9200/.watch_history*/_search?pretty' -d '{
  "sort" : [
    { "result.execution_time" : "desc" }
  ]
}'


# 可以在 elasticsearch 的日志中打印日志

curl -XPUT 'http://localhost:9200/_watcher/watch/log_error_watch' -d '{
  "trigger" : { "schedule" : { "interval" : "10s" } },
  "input" : {
    "search" : {
      "request" : {
        "indices" : [ "db_order" ],
        "body" : {
          "query" : {
            "match" : { "phone": "15652636152" }
          }
        }
      }
    }
  },
  "condition" : {
    "compare" : { "ctx.payload.hits.total" : { "gt" : 0 }}
  },
  "actions" : {
  "send_trigger" : {
    "throttle_period" : "5m",
    "webhook" : {
      "method" : "POST",
      "host" : "https://events.pagerduty.com",
      "port" : 443,
      "path": ":/generic/2010-04-15/create_event.json}",
      "body" : "{\
        \"service_key\": \"e93facc04764012d7bfb002500d5d1a6\",
        \"incident_key\": \"long_watches\",
        \"event_type\": \"trigger\",
        \"description\": \"{{ctx.payload.hits.total}} watches took more than 2.5 seconds to execute\",
        \"client\": \"Watcher\"
      }"
      "headers": {"Content-type": "application/json"}
    }
  }
}
}'