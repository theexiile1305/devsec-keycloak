package dev.mfuchs.devseckeycloak.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "swagger", ignoreUnknownFields = false)
class ApiDocumentationConfigProperties {
    lateinit var tokenRequestEndpoint: String
    lateinit var tokenEndpoint: String
}