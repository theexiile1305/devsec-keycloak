package dev.mfuchs.devseckeycloak.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter

@Configuration
class JwtAuthenticationConverterConfig {

    companion object {
        const val JWT_ROLE_PREFIX = "ROLE_"
        private const val JWT_ROLE_NAME = "roles"
    }

    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter = JwtAuthenticationConverter().apply {
        setJwtGrantedAuthoritiesConverter(
            JwtGrantedAuthoritiesConverter().apply {
                setAuthoritiesClaimName(JWT_ROLE_NAME)
                setAuthorityPrefix(JWT_ROLE_PREFIX)
            }
        )
    }
}