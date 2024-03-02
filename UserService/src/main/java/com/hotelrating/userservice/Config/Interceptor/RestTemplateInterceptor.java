package com.hotelrating.userservice.Config.Interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private OAuth2AuthorizedClientManager authorizedClientManager;

    public RestTemplateInterceptor(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        OAuth2AuthorizeRequest oAuth2AuthorizeRequest=OAuth2AuthorizeRequest
                .withClientRegistrationId("my-internal-client") // name of the client/ our user service in Properties
                .principal("internal")
                .build();

        String token=authorizedClientManager.authorize(oAuth2AuthorizeRequest).getAccessToken().getTokenValue();

        request.getHeaders().add("Authorization" , "Bearer "+ token);

        execution.execute(request, body);

        return null;
    }
}
