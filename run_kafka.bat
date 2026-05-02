@echo off
set KAFKA_DIR=C:\kafka
set CONFIG=c:\Users\yassi\Desktop\AlgoNum_project\Tp_Kafka\server.properties

echo [1/3] Generation du Cluster ID...
for /f "tokens=*" %%i in ('call %KAFKA_DIR%\bin\windows\kafka-run-class.bat kafka.tools.StorageTool random-uuid') do set CLUSTER_ID=%%i
echo Cluster ID genere : %CLUSTER_ID%

echo [2/3] Formatage du stockage...
call %KAFKA_DIR%\bin\windows\kafka-run-class.bat kafka.tools.StorageTool format --config %CONFIG% --cluster-id %CLUSTER_ID%

echo [3/3] Demarrage du broker Kafka...
call %KAFKA_DIR%\bin\windows\kafka-server-start.bat %CONFIG%

