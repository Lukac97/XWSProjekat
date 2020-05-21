#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar cartservice-1.0.0.jar