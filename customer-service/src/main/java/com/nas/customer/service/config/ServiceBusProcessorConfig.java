package com.nas.customer.service.config;



import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.spring.cloud.service.servicebus.consumer.ServiceBusErrorHandler;
import com.azure.spring.cloud.service.servicebus.consumer.ServiceBusRecordMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@Configuration(proxyBeanMethods = false)
public class ServiceBusProcessorConfig {

    private static final String SERVICE_BUS_FQDN = "<service-bus-fully-qualified-namespace>";
    private static final String QUEUE_NAME = "<service-bus-queue-name>";


    @Bean
    ServiceBusClientBuilder serviceBusClientBuilder(){
        return new ServiceBusClientBuilder()
                .fullyQualifiedNamespace(SERVICE_BUS_FQDN)
                .credential(new DefaultAzureCredentialBuilder()
                        .build());
    }

    @Bean
    ServiceBusRecordMessageListener processMessage() {
        return context -> {
            ServiceBusReceivedMessage message = context.getMessage();
            System.out.printf("[+] Processing message " + message.getMessageId());
            System.out.printf("[+] Sequence  " + message.getSequenceNumber());
            System.out.printf("[+] Contents " + message.getBody());
        };
    }

    @Bean
    ServiceBusErrorHandler processError() {
        return context -> {
            System.out.printf("Error when receiving messages from namespace: '%s'. Entity: '%s'%n",
                    context.getFullyQualifiedNamespace(), context.getEntityPath());
        };
    }
}
