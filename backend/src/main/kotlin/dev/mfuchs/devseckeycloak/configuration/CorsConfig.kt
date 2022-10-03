package dev.mfuchs.devseckeycloak.configuration

import dev.mfuchs.devseckeycloak.properties.WebProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpMethod.PUT
import org.springframework.http.HttpMethod.DELETE
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig(
    private val webProperties: WebProperties
) {

    @Bean
    fun corsFilter(): CorsFilter? {
        val corsConfig = CorsConfiguration()
            .applyPermitDefaultValues()
            .apply {
                allowedMethods = listOf(GET.name, POST.name, PUT.name, DELETE.name)
                allowedOrigins = webProperties.cors.allowedOrigins
                allowCredentials = true
                addExposedHeader("Content-Disposition")
            }

        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfig)
        }

        return CorsFilter(source)
    }
}