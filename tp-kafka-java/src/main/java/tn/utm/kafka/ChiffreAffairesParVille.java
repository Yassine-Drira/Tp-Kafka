package tn.utm.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ChiffreAffairesParVille {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "groupe-neuf-ca");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Map<String, Double> caParVille = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try (Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList("pos-events"));
            System.out.println("Analyse du Chiffre d'Affaires demarree...");

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                for (ConsumerRecord<String, String> record : records) {
                    JsonNode event = mapper.readTree(record.value());
                    
                    if (event.get("type").asText().equals("VENTE")) {
                        String ville = event.get("ville").asText();
                        double montant = event.get("montant").asDouble();
                        
                        caParVille.put(ville, caParVille.getOrDefault(ville, 0.0) + montant);
                        
                        System.out.println("--- Mise a jour CA ---");
                        caParVille.forEach((v, ca) -> System.out.printf(" %s : %.2f DT%n", v, ca));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
