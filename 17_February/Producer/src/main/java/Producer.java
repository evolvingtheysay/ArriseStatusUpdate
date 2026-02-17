import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class Producer {

    public static void main(String[] args) throws InterruptedException {

        String producerName = "Producer-1"; // Yeh change karo Producer-2, Producer-3 ke liye

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        System.out.println(producerName + " started! Sending messages...");

        for (int i = 1; i <= 100; i++) {
            String message = "Hello from " + producerName + "! Message #" + i;
            producer.send(new ProducerRecord<>("my-multi-topic", message));
            System.out.println("Sent: " + message);
            Thread.sleep(1000);
        }

        producer.close();
        System.out.println(producerName + " done!");
    }
}