# TP Apache Kafka - Guide d'exécution

Ce projet contient les exemples de code pour le TP Kafka.

## 1. Préparation de Kafka (KRaft)
Assurez-vous d'avoir extrait Kafka dans `C:\kafka`.

```powershell
# Générer un ID de cluster
$KAFKA_CLUSTER_ID = C:\kafka\bin\windows\kafka-storage.bat random-uuid

# Formater le stockage (utiliser le fichier server.properties à la racine du projet)
C:\kafka\bin\windows\kafka-storage.bat format -t $KAFKA_CLUSTER_ID -c .\server.properties

# Démarrer Kafka
C:\kafka\bin\windows\kafka-server-start.bat .\server.properties
```

## 2. Création des Topics
Ouvrez un nouveau terminal et créez les topics nécessaires :

```powershell
cd C:\kafka\bin\windows
.\kafka-topics.bat --create --topic ventes --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
.\kafka-topics.bat --create --topic commandes --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
.\kafka-topics.bat --create --topic pos-events --bootstrap-server localhost:9092 --partitions 4 --replication-factor 1
.\kafka-topics.bat --create --topic alertes-retours --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

## 3. Compilation du projet Java
```powershell
cd c:\Users\yassi\Desktop\AlgoNum_project\Tp_Kafka\tp-kafka-java
mvn clean package
```

## 4. Exécution des exercices

### Partie 4 : Producer / Consumer Simple
```powershell
# Terminal 1 : Consommateur
mvn exec:java -Dexec.mainClass="tn.utm.kafka.SimpleConsumer"

# Terminal 2 : Producteur
mvn exec:java -Dexec.mainClass="tn.utm.kafka.SimpleProducer"
```

### Partie 4.4 : JSON Serialization
```powershell
# Terminal 1 : Consommateur JSON
mvn exec:java -Dexec.mainClass="tn.utm.kafka.JsonConsumer"

# Terminal 2 : Producteur JSON
mvn exec:java -Dexec.mainClass="tn.utm.kafka.JsonProducer"
```

### Partie 6 : Mini-Projet Pipeline
```powershell
# Terminal 1 : Simulateur de caisse
mvn exec:java -Dexec.mainClass="tn.utm.kafka.SimulateurCaisse"

# Terminal 2 : Calculateur de CA
mvn exec:java -Dexec.mainClass="tn.utm.kafka.ChiffreAffairesParVille"

# Terminal 3 : Détecteur d'anomalies
mvn exec:java -Dexec.mainClass="tn.utm.kafka.DetecteurAnomalies"
```
