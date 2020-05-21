#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar rentingservice-1.0.0.jar