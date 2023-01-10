# Mini Uber
Mini Uber is a ride sharing that hires independent contractors as drivers.

# Setup On Mac
On your teminal put:
```
brew install update
```
```
brew install kafka
```
Or Downloads binary source from [Apache Kafka](https://kafka.apache.org/downloads)
```
cd Donwloads
```
```
~/kafka_2.13-3.1.0/bin/zookeeper-server-start.sh ~/kafka_2.13-3.1.0/config/zookeeper.properties
```
- Setup the $PATH environment variable
```
PATH="$PATH:/Users/yourusername/kafka_2.13-3.1.0/bin"
```

# Apache Kafka Example
Consumer Config
```java
@Configuration
public class ConsumerConfig{
    public ConsumerFactory<String, String> consumerFactory(){
        Map<String, String> map = new HashMap<>();


    }
}
```
``` java
@Slf4j
@RequiredArgsConstructor
@Service
public class TopicConsumer {

    @Value("${topic.name.consumer") // get it from application.yml
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
    }
}
```
Producer
```java
@Service
@Slf4j
@RequiredArgsConstructor
public class TopicProducer {

    @Value("${topic.name.producer}") // get it from application.yml
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){
        log.info("Payload envi: {}", message);
        kafkaTemplate.send(topicName, message);
    }
}

```

# Config Server

# Circuit Breaker

# Edge Server