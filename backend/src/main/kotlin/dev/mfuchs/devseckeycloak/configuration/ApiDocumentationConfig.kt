package dev.mfuchs.devseckeycloak.configuration

import dev.mfuchs.devseckeycloak.properties.ApiDocumentationConfigProperties
import dev.mfuchs.devseckeycloak.rest.NoteController
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.*
import io.swagger.v3.oas.models.tags.Tag
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition
class ApiDocumentationConfig(
    private val properties: ApiDocumentationConfigProperties
) {

    companion object {
        private const val SECURITY_SCHEME = "security_auth"
    }

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(info())
        .addTagsItem(Tag().name(NoteController.TAG).description(NoteController.DESCRIPTION))
        .addSecurityItem(SecurityRequirement().addList(SECURITY_SCHEME))
        .components(securityScheme())

    private fun info(): Info = Info()
        .title("Notes-Service")
        .description("Simple CRUD operations for demonstration purposes")

    private fun securityScheme() = Components()
        .addSecuritySchemes(
            SECURITY_SCHEME,
            SecurityScheme()
                .`in`(SecurityScheme.In.HEADER)
                .bearerFormat("jwt")
                .type(SecurityScheme.Type.OAUTH2)
                .flows(
                    OAuthFlows()
                        .authorizationCode(
                            OAuthFlow()
                                .authorizationUrl(properties.tokenRequestEndpoint)
                                .tokenUrl(properties.tokenEndpoint)
                                .scopes(Scopes())
                        )
                )
        )
}
