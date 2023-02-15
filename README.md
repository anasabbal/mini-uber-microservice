# Mini Uber
![logo](./img/back.png)
NUBER is a ride sharing that hires independent contractors as drivers.

# Table of contents
- [Introduction](#introduction)
- [Talk for each service](#talk-for-each-service)
    - [Customer service](#customer-service)
    - [Driver serivce](#driver-service)
    - [Driver location service](#)
    - [Api gateway service](#)
    - [Config service](#)
    - [Payment service](#)
    - [Wallet service](#)
    - [Rating service](#)
- [Setup On Mac](#setup-on-mac)
- [Even Driven](#)
  - [Rabbit MQ](#)
- [Docker](#docker)
    - [Build](#build)
    - [Run locally](#run-locally)


# Introduction
# Talk for Each service
##### Customer Service
- create account in Nuber 
- update profile
- get available drivers 
- send request to driver
- payment
- find job from Nuber carriers
- earn money from sending link to another client that create account in Nuber
- post rating for driver

##### Driver Service
# Setup On Mac

```
SSH: git@github.com:anasabbal/mini-uber-microservice.git
HTTPS: https://github.com/anasabbal/mini-uber-microservice.git
cd mini-uber-microservice
mvn clean install compile
mvn spring-boot:run
```
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
# Basic Kafka
Apache Kafka depends on Zookeeper for cluster management, first step we need to start Zookeeper
```
➜  ~zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
[2023-01-10 01:55:30,922] INFO KafkaConfig values:
	advertised.listeners = null
	alter.config.policy.class.name = null
	alter.log.dirs.replication.quota.window.num = 11
	alter.log.dirs.replication.quota.window.size.seconds = 1
	authorizer.class.name =
	auto.create.topics.enable = true
	auto.leader.rebalance.enable = true
	background.threads = 10
	broker.heartbeat.interval.ms = 2000
	broker.id = 0
	broker.id.generation.enable = true
	broker.rack = null
	broker.session.timeout.ms = 9000
	.
	.
	.
```
After that we run kafka server
```
➜  ~kafka-server-start /usr/local/etc/kafka/server.properties
Manager$ThrottledChannelReaper)
[2023-01-10 01:58:54,857] INFO [ThrottledChannelReaper-Request]: Starting (kafka.server.ClientQuotaManager$ThrottledChannelReaper)
[2023-01-10 01:58:54,864] INFO [ThrottledChannelReaper-ControllerMutation]: Starting (kafka.server.ClientQuotaManager$ThrottledChannelReaper)
[2023-01-10 01:58:55,015] INFO Loading logs from log dirs ArraySeq(/usr/local/var/lib/kafka-logs) (kafka.log.LogManager)
[2023-01-10 01:58:55,029] INFO Skipping recovery for all logs in /usr/local/var/lib/kafka-logs since clean shutdown file was found (kafka.log.LogManager)
[2023-01-10 01:58:55,097] INFO Loaded 0 logs in 81ms. (kafka.log.LogManager)
[2023-01-10 01:58:55,100] INFO Starting log cleanup with a period of 300000 ms. (kafka.log.LogManager)
[2023-01-10 01:58:55,111] INFO Starting log flusher with a default period of 9223372036854775807 ms. (kafka.log.LogManager)
[2023-01-10 01:58:55,700] INFO [feature-zk-node-event-process-thread]: Starting (kafka.server.FinalizedFeatureChangeListener$ChangeNotificationProcessorThread)
[2023-01-10 01:58:55,801] INFO [MetadataCache brokerId=0] Updated cache from existing <empty> to latest FinalizedFeaturesAndEpoch(features=Map(), epoch=0). (kafka.server.metadata.ZkMetadataCache)
[2023-01-10 01:58:55,930] INFO [BrokerToControllerChannelManager broker=0 name=forwarding]: Starting (kafka.server.BrokerToControllerRequestThread)
[2023-01-10 01:58:57,421] INFO Updated connection-accept-rate max connection creation rate to 2147483647 (kafka.network.ConnectionQuotas)
```
Creat a topic is a category or feed name to which records are published. Topics in Kafka are always multi-subscriber; that is, a topic can have zero, one, or many consumers that subscribe to the data written to it
```
$ kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
```
Initialize Producer console
```
$ kafka-console-producer --broker-list localhost:9092 --topic test
>send first message
>send second message
>wow it is working
```
Initialize Consumer console
```
$ kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginningsend 
first message
send second message
wow it is working
```
!!! Congratulation
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
