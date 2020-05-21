#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar advertisementservice-1.0.0.jar