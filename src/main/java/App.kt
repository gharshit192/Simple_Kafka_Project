import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

fun runProducer(){
    println("\n Producer Running.... \n")
    try {
        val producer = MyProducer().createProducer()
        for (i in 10 until  KafkaConstants.MESSAGE_COUNT) {
            producer.send(ProducerRecord(KafkaConstants.TOPIC_NAME, "$i", "Produced $i")).get()
            println("Produced $i")
        }
    }catch (e: Exception){
        e.printStackTrace()
    }
    println("\n Producer Finished.... \n")

}
fun runConsumer() {
    println("\n Consumer Running.... \n")
    val consumer = MyConsumer().createConsumer()
    try {
        consumer.subscribe(listOf(KafkaConstants.TOPIC_NAME))
        println("\n Subscribed to ${KafkaConstants.TOPIC_NAME}\n")
        while (true) {
            val consumeRecords = consumer.poll(100)
            for (record in consumeRecords) {
                println("offset = ${record.offset()} , key = ${record.key()} , value = ${record.value()}")
                println("Partition Consumed ${record.partition()}")
            }
        }
    }catch (e: Exception){
        e.printStackTrace()
}
    println("\n Consumer Finished.... \n")
}


    fun main() {
        val inputKey = Scanner(System.`in`)

        print("Enter 1 for Producer and 2 for Consumer : ")
        when (inputKey.nextInt()) {
            1 -> runProducer()
            2 -> runConsumer()
        }
    }

