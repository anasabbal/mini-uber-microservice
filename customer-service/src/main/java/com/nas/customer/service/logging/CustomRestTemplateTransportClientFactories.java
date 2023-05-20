package com.nas.customer.service.logging;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.transport.TransportClientFactory;
import org.springframework.cloud.netflix.eureka.http.RestTemplateDiscoveryClientOptionalArgs;
import org.springframework.cloud.netflix.eureka.http.RestTemplateTransportClientFactories;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.util.Collection;
import java.util.Optional;

public class CustomRestTemplateTransportClientFactories extends RestTemplateTransportClientFactories {

    public CustomRestTemplateTransportClientFactories(RestTemplateDiscoveryClientOptionalArgs args) {
        super(args);
    }
    @Override
    public TransportClientFactory newTransportClientFactory(final EurekaClientConfig clientConfig,
                                                            final Collection<Void> additionalFilters, final InstanceInfo myInstanceInfo,
                                                            final Optional<SSLContext> sslContext, final Optional<HostnameVerifier> hostnameVerifier) {
        return new CustomRestTemplateTransportClientFactory();
    }
}
