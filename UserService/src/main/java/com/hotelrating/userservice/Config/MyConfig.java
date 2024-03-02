package com.hotelrating.userservice.Config;

import com.hotelrating.userservice.Config.Interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig {

    private ClientRegistrationRepository clientRegistrationRepository;
    private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

    @Autowired
    public MyConfig(ClientRegistrationRepository clientRegistrationRepository,
                    OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.oAuth2AuthorizedClientRepository = oAuth2AuthorizedClientRepository;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){

        RestTemplate restTemplate= new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors= new ArrayList<>();
            interceptors.add(new RestTemplateInterceptor( authorizedClientManager(
                    clientRegistrationRepository, oAuth2AuthorizedClientRepository
            ) ));

         restTemplate.setInterceptors(interceptors);

        return new RestTemplate();
    }


    // Declaring Bean for OAuth2AuthorizedClientManager authorizedClientManager in Interceptor->FeignClientInterceptor.class
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
    ){
        OAuth2AuthorizedClientProvider provider= OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();

        DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager= new DefaultOAuth2AuthorizedClientManager(
                                                    clientRegistrationRepository, oAuth2AuthorizedClientRepository);

        defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);

        return defaultOAuth2AuthorizedClientManager;
    }

}
