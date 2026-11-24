#!/bin/sh
project=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd) || exit 1
exec "${JAVA_HOME:+$JAVA_HOME/bin/}java" -jar "$project/gradle/wrapper/gradle-wrapper.jar" "$@"
