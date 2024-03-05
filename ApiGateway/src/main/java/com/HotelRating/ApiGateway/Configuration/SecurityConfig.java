package com.HotelRating.ApiGateway.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity // Because spring-cloud-starter-gateway support spring webflux.
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
        httpSecurity.authorizeExchange( exchanges-> exchanges.anyExchange().authenticated())
                .oauth2Client(Customizer.withDefaults())    // configuring oauth login
                .oauth2ResourceServer( oAuth2 -> oAuth2.jwt( Customizer.withDefaults()));   // configuring oauth server

        return httpSecurity.build();

    }
    /*  JWT DECODER is Used complying with @EnableWebSecurity  in compare to API Gateway where REACTIVE JWT DECODER with @EnableWebFluxSecurity */
    /* ---->JWT Reactive Decoder & JWT Decoder are same just with Diff names Complying with Diff Annotation */
    /* ------>We can define JWT Reactive Decoder over here or in Configuration .YML file

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromIssuerLocation("https://dev-30011393.okta.com/oauth2/default");
        //      issuer-uri: https://dev-30011393.okta.com/oauth2/default
    }
    **/
}
