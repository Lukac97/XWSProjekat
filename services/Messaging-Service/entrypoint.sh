#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar messagingservice-1.0.0.jar