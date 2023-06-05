package com.nas.customer.service.config;


import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.servicebus.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ServiceBudClientConfiguration {
     /*private static final String SERVICE_BUS_FQDN = "customerbus.servicebus.windows.net";
    private static final String QUEUE_NAME = "eventcus";

    @Bean
    ServiceBusClientBuilder serviceBusClientBuilder(){
        return new ServiceBusClientBuilder()
                .fullyQualifiedNamespace(SERVICE_BUS_FQDN)
                .credential(new DefaultAzureCredentialBuilder().build());
    }
    @Bean
    ServiceBusSenderClient serviceBusSenderClient(ServiceBusClientBuilder serviceBusClientBuilder){
        return serviceBusClientBuilder
                .sender()
                .queueName(QUEUE_NAME)
                .buildClient();
    }
   @Bean
    ServiceBusProcessorClient serviceBusProcessorClient(ServiceBusClientBuilder builder) {
        return builder.processor()
                .queueName(QUEUE_NAME)
                .processMessage(ServiceBudClientConfiguration::processMessage)
                .processError(ServiceBudClientConfiguration::processError)
                .buildProcessorClient();
    }

    private static void processMessage(ServiceBusReceivedMessageContext context) {
        ServiceBusReceivedMessage message = context.getMessage();
        System.out.printf("Processing message. Id: %s, Sequence #: %s. Contents: %s%n",
                message.getMessageId(), message.getSequenceNumber(), message.getBody());
    }

    private static void processError(ServiceBusErrorContext context) {
        System.out.printf("Error when receiving messages from namespace: '%s'. Entity: '%s'%n",
                context.getFullyQualifiedNamespace(), context.getEntityPath());
    }*/
}
