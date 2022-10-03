package dev.mfuchs.devseckeycloak.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "web", ignoreUnknownFields = false)
class WebProperties {

    var cors: Cors = Cors()

    class Cors {
        var allowedOrigins: List<String> = emptyList()
    }
}