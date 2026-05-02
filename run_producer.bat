@echo off
C:\kafka\bin\windows\kafka-console-producer.bat --bootstrap-server localhost:9092 --topic logs-application --property "parse.key=true" --property "key.separator=:"
