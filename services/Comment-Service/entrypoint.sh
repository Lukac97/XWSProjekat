#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar commentservice-1.0.0.jar