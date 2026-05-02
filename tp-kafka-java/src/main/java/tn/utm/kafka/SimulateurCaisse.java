package tn.utm.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Instant;
import java.util.Properties;
import java.util.Random;

public class SimulateurCaisse {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        
        // Version simplifiee pour Windows
        props.put(ProducerConfig.ACKS_CONFIG, "1");

        String[] villes = {"Tunis", "Sousse", "Sfax", "Bizerte", "Gabes"};
        Random random = new Random();
        ObjectMapper mapper = new ObjectMapper();

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            System.out.println("Simulateur de caisse demarre...");

            while (true) {
                String ville = villes[random.nextInt(villes.length)];
                String type = genererType(random);
                double montant = 5 + (500 - 5) * random.nextDouble();

                ObjectNode event = mapper.createObjectNode();
                event.put("type", type);
                event.put("idCaisse", "CAISSE-" + ville.toUpperCase() + "-" + random.nextInt(10));
                event.put("ville", ville);
                event.put("timestamp", Instant.now().toString());
                
                if (!type.equals("OUVERTURE")) {
                    event.put("montant", Math.round(montant * 100.0) / 100.0);
                }

                String json = mapper.writeValueAsString(event);
                
                // Partitionnement par ville (cle = ville)
                ProducerRecord<String, String> record = new ProducerRecord<>("pos-events", ville, json);

                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        System.err.println("ERREUR d'envoi : " + exception.getMessage());
                    } else {
                        System.out.println("Evenement envoye vers partition " + metadata.partition() + " : " + json);
                    }
                });

                // Pause aleatoire entre 100 et 500 ms
                Thread.sleep(100 + random.nextInt(400));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String genererType(Random r) {
        int p = r.nextInt(100);
        if (p < 70) return "VENTE";
        if (p < 80) return "RETOUR";
        return "OUVERTURE";
    }
}
