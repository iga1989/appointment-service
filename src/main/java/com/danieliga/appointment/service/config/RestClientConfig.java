package com.danieliga.appointment.service.config;

import io.micrometer.observation.ObservationRegistry;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    private final ObservationRegistry observationRegistry;

    public RestClientConfig(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
    }

    @LoadBalanced
    @Bean
    RestClient.Builder restClientBuilder(JwtInterceptor jwtInterceptor) {
        return RestClient.builder().requestInterceptor(jwtInterceptor);

    }

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder
                .baseUrl("http://api-gateway")
                .observationRegistry(observationRegistry)
                .build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        return HttpServiceProxyFactory
                .builderFor(restClientAdapter)
                .build();
    }

    @Bean
    public HealthProviderClient healthProviderClient(HttpServiceProxyFactory httpServiceProxyFactory) {
        return httpServiceProxyFactory.createClient(HealthProviderClient.class);
    }

}
