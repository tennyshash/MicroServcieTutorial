package com.hotelrating.userservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true) DEPRECATED
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( request-> request.anyRequest().authenticated())
                .oauth2ResourceServer( oAuth2 -> oAuth2.jwt( Customizer.withDefaults()));   // configuring oauth server

        return http.build();
    }

    /*-->JWT DECODER is Used complying with @EnableWebSecurity  in compare to API Gateway where REACTIVE JWT DECODER  with @EnableWebFluxSecurity
    *--->JWT Reactive Decoder & JWT Decoder are same just with Diff names Complying with Diff Annotation
    * ------>We can define JWT Decoder over here or in Configuration .YML file
     **/
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return JwtDecoders.fromIssuerLocation("https://dev-30011393.okta.com/oauth2/default");
//        //      issuer-uri: https://dev-30011393.okta.com/oauth2/default
//    }

}
