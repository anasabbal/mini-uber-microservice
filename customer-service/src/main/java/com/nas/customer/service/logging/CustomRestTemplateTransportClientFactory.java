package com.nas.customer.service.logging;

import com.nas.customer.service.config.AccessTokenManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.http.RestTemplateEurekaHttpClient;
import org.springframework.cloud.netflix.eureka.http.RestTemplateTransportClientFactory;
import com.netflix.discovery.shared.resolver.EurekaEndpoint;
import com.netflix.discovery.shared.transport.EurekaHttpClient;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;



@Slf4j
public class CustomRestTemplateTransportClientFactory extends RestTemplateTransportClientFactory {

    @Override
    public EurekaHttpClient newClient(EurekaEndpoint serviceUrl) {
        try {
            return new RestTemplateEurekaHttpClient(customRestTemplate(),
                    serviceUrl.getServiceUrl());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public class RequestResponseHandlerInterceptor implements ClientHttpRequestInterceptor {

        private static final String AUTHORIZATION = "Authorization";

        /**
         * This method will intercept every request and response and based on response status code if its 401 then will retry
         * once
         */

        @SneakyThrows
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) {

            String accessToken = AccessTokenManager.getToken();
            request.getHeaders().remove(AUTHORIZATION);
            request.getHeaders().add(AUTHORIZATION, "Bearer " + accessToken);

            ClientHttpResponse response = execution.execute(request, body);
            return response;
        }
    }
    private RestTemplate customRestTemplate() {
        /*
         * Inject your custom rest template
         */
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors()
                .add(new RequestResponseHandlerInterceptor());
        RestTemplateTransportClientFactory factory = new RestTemplateTransportClientFactory();

        restTemplate.getMessageConverters().add(0, factory.mappingJacksonHttpMessageConverter());

        return restTemplate;
    }
}