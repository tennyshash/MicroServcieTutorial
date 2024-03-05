package com.hotelrating.userservice.Config.Interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private Logger logger= LoggerFactory.getLogger(FeignClientInterceptor.class);

    private OAuth2AuthorizedClientManager authorizedClientManager;
    @Autowired
    public FeignClientInterceptor(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    //Method runs before sending request
    @Override
    public void apply(RequestTemplate requestTemplate) {



        OAuth2AuthorizeRequest oAuth2AuthorizeRequest=OAuth2AuthorizeRequest
                .withClientRegistrationId("my-internal-client") // name of the client/ our user service in Properties
                .principal("internal")
                .build();

        String token=authorizedClientManager.authorize(oAuth2AuthorizeRequest).getAccessToken().getTokenValue();

        requestTemplate.header("Authorization" , "Bearer "+ token);
    }
}
