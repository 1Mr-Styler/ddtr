#!/usr/bin/env bash

export JAVA_HOME=/root/.sdkman/candidates/java/current

echo "Running DDTR Server"
./gradlew bootRun
