package co.basketlove.api.config.openapi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI basketLoveOpenAPI() {

        return new OpenAPI()

                .info(

                        new Info()
                                .title("Basket Love API")
                                .description(
                                        "Backend API for Basket Love ecommerce platform"
                                )
                                .version("v1.0.0")

                                .contact(
                                        new Contact()
                                                .name("Basket Love")
                                                .url("https://basketlove.tengohambre.co")
                                                .email("support@basketlove.tengohambre.co")
                                )

                                .license(
                                        new License()
                                                .name("Private License")
                                )
                )

                .externalDocs(
                        new ExternalDocumentation()
                                .description("Basket Love Documentation")
                                .url("https://basketlove.tengohambre.co")
                );
    }
}