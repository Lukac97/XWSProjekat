#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar userservice-1.0.0.jar