import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import java.util.*

class MyProducer {

    fun createProducer(): KafkaProducer<String, String> {
        val property = Properties()
        property[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = KafkaConstants.SERVER
        property[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = KafkaSerializer::class.java
        property[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = KafkaSerializer::class.java
        return KafkaProducer(property)
    }

}