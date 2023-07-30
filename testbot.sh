#!/usr/bin/env bash

while read line; do
    export "$line"
done < .env
java -jar build/libs/time-supporter-bot-1.0-SNAPSHOT-all.jar