package com.hotelrating.hotelservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true) DEPRECATED
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests( request-> request.anyRequest().authenticated())
                .oauth2ResourceServer( oauth2-> oauth2.jwt(Customizer.withDefaults()));

        return httpSecurity.build();
    }

    /*-->JWT DECODER is Used complying with @EnableWebSecurity  in compare to API Gateway where REACTIVE JWT DECODER  with @EnableWebFluxSecurity
    *--->JWT Reactive Decoder & JWT Decoder are same just with Diff names Complying with Diff Annotation
    * ------>We can define JWT Decoder over here or in Configuration .YML file
    * *--->For Hotel service we'll use Defining in Configuration approach & for rating service we'll use defining in Config Class
    *
    *
    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("https://dev-30011393.okta.com/oauth2/default");
        //      issuer-uri: https://dev-30011393.okta.com/oauth2/default
    }
    *                */

}
