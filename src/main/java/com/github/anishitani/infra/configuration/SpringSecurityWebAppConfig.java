package com.github.anishitani.infra.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-uri}")
    String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-id}")
    String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-secret}")
    String clientSecret;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authz -> authz.anyRequest().authenticated())
                .oauth2ResourceServer(
                        oauth2 -> oauth2.opaqueToken(
                                token -> token.introspectionUri(this.introspectionUri)
                                        .introspectionClientCredentials(this.clientId, this.clientSecret)));
    }
}