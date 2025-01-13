#!/bin/bash

arg="mvn"

if [ $# -gt 0 ]; then
arg=$1; fi

$arg compile
$arg exec:java -Dexec.mainClass="com.example.handwritinggeneratorutility.Main"