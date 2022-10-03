package dev.mfuchs.devseckeycloak.configuration

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val jwtAuthenticationConverter: JwtAuthenticationConverter
) {

    companion object {
        private val logger = LoggerFactory.getLogger(SecurityConfig::class.java)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http
        .cors {}
        .csrf { it.disable() }
        .authorizeRequests { it
            .antMatchers("/swagger-ui.html", "/swagger-ui/**").permitAll()
            .antMatchers("/v3/api-docs", "/v3/api-docs.yaml", "/v3/api-docs/swagger-config").permitAll()
            .anyRequest().authenticated() }
        .oauth2ResourceServer { it
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter)
        }.formLogin { it
            .disable()
            .exceptionHandling { exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer
                    .accessDeniedHandler { request, response, _ ->
                        request.run {
                            logger.error("User ${userPrincipal.name} doesn't have enough privilege requesting $method $requestURI")
                        }
                        response.sendError(HttpStatus.FORBIDDEN.value(), "User doesn't have enough privilege")
                    }.authenticationEntryPoint { request, response, _ ->
                        request.run {
                            logger.error("User has not logged-in requesting $method $requestURI")
                        }
                        response.sendError(HttpStatus.UNAUTHORIZED.value(), "User has not logged-in")
                    }
            }
        }.build()
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@PreAuthorize(
    """
		hasRole(T(dev.mfuchs.devseckeycloak.configuration.Role).NOTES_WRITE) 
		"""
)
annotation class NotesWriteAuthorization

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@PreAuthorize(
    """
		hasRole(T(dev.mfuchs.devseckeycloak.configuration.Role).NOTES_READ) 
		"""
)
annotation class NotesReadAuthorization

object Role {
    const val NOTES_WRITE = "NOTES_WRITE"
    const val NOTES_READ = "NOTES_READ"
}