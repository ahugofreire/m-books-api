package mercadolivro.com.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

//@Profile("!prod")
@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI().info(
            Info().title("M BOOK API")
                .description("This API provides resources for Mercado Livro.")
        )
    }
}
