package org.example.ecommerce.unsplash;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@ConditionalOnProperty(name = "unsplash.enabled", havingValue = "true", matchIfMissing = true)
public class RestClientConfig {

    @Value("${unsplash.base-url}")
    private String unsplashBaseUrl;

    @Value("${unsplash.access-key}")
    private String unsplashApiAccessKey;

    @Bean
    public RestClient unsplashRestClient() {
        return RestClient.builder()
                .baseUrl(unsplashBaseUrl)
                .defaultHeader("Authorization", "Client-ID " + unsplashApiAccessKey)
                .build();
    }

    @Bean
    public UnsplashRestService unsplashRestService(RestClient unsplashRestClient) {
        RestClientAdapter adapter = RestClientAdapter.create(unsplashRestClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(UnsplashRestService.class);
    }
}
