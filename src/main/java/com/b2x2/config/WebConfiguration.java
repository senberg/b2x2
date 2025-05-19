package com.b2x2.config;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
        return (TomcatServletWebServerFactory factory) -> {
            Connector connector = new Connector();
            connector.setPort(80);
            factory.addAdditionalTomcatConnectors(connector);
        };
    }
}
