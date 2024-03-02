package com.HotelRating.ApiGateway.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
        httpSecurity.authorizeExchange( exchanges-> exchanges.anyExchange().authenticated())
                .oauth2Client(Customizer.withDefaults())    // configuring oauth login
                .oauth2ResourceServer( oAuth2 -> oAuth2.jwt( Customizer.withDefaults()));   // configuring oauth server

        return httpSecurity.build();

    }
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromIssuerLocation("https://dev-30011393.okta.com/oauth2/default");
        //      issuer-uri: https://dev-30011393.okta.com/oauth2/default
    }
}
