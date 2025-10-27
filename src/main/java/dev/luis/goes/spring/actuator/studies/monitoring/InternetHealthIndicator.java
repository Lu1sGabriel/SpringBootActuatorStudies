package dev.luis.goes.spring.actuator.studies.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

@Component
public class InternetHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return isInternetAvailable() ? Health.up().build() : Health.down().build();
    }

    private boolean isInternetAvailable() {
        try {
            URI uri = URI.create("https://www.google.com.br");
            URL url = uri.toURL();
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(2000);
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}