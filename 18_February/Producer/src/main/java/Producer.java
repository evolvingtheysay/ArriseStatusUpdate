import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class Producer {

    public static void main(String[] args) throws InterruptedException {

        String producerName = "Producer-3"; // Producer 2 aur 3 ke liye change karo
        int producerId = 1;

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        System.out.println(producerName + " started! Sending messages...");

        for (int i = 1; i <= 100; i++) {
            // Key se partition decide hota hai, har producer alag partition pe jayega
            String key = "producer-" + producerId;
            String message = "Hello from " + producerName + " | Message #" + i;
            producer.send(new ProducerRecord<>("my-multi-topic", key, message));
            System.out.println("Sent: " + message);
            Thread.sleep(500);
        }

        producer.close();
        System.out.println(producerName + " done");
    }
}