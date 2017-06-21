package io.kami.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class VertxConfiguration {

    @Autowired
    Environment environment;

    int httpPort() {
        return environment.getProperty("http.port", Integer.class, 8080);
    }
}
