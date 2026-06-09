@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Passly API")
                .description("API da plataforma de turismo Passly")
                .version("1.0.0")
            );
    }
}
