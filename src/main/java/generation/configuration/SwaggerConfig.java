package generation.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springBlogPessoalOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto Blog")
                        .description("Projeto Blogo para prática de uso de Spring")
                        .version("v0.0.2")
                        .license(new License()
                                .name("Edson Junior")
                                .url("https://www.donotenter.com"))
                        .contact(new Contact()
                                .name("Edson Junior")
                                .url("https://www.donotenter.com")
                                .email("edson@email.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/efojunior"));
    }

    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Sucesso"));
                apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido"));
                apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído"));
                apiResponses.addApiResponse("400", createApiResponse("Erro na requisição"));
                apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado"));
                apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado"));
                apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação"));
            }));
        };
    }

    private ApiResponse createApiResponse(String message){
        return new ApiResponse().description(message);
    }
}