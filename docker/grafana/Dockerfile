FROM alpine:3.4

RUN apk update && apk upgrade && \
    apk add --no-cache \
    curl

ADD *.json ./

CMD sleep 5 && \
    curl --header 'Content-Type: application/json' --data @datasource.json -u $GRAFANA_USER:$GRAFANA_PASS http://grafana-server:3000/api/datasources && \
    curl --header 'Content-Type: application/json' --data @PerfMonitor.json -u $GRAFANA_USER:$GRAFANA_PASS http://grafana-server:3000/api/dashboards/db


