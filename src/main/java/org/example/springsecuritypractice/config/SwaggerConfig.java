package org.example.springsecuritypractice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SwaggerConfig {
    @Value("${project.version}")
    private String version;

    @Value("${server.port}")
    private String port;

    @Bean
    public OpenAPI customOpenAPI() {
        var contact = new Contact();
        contact.setEmail("luigivis98@gmail.com");
        contact.setName("Luigi Vismara");
        contact.setUrl("https://www.github.com/luigivis");

        var mitLicense = new License().name("MIT License").url(STR."http://localhost:\{port}/license");

        var components = new Components();
        components.setSchemas(schemas());

        Info info = new Info()
                .title("Spring Security Web API")
                .version(version)
                .contact(contact)
                .description("This project is for testing purposes.")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }

    @Bean
    public OpenApiCustomizer genericResponseCustomizer() {
        return openApi -> {
            openApi.getPaths().forEach((path, pathItem) -> {
                pathItem.readOperations().forEach(operation -> {
                    operation.getResponses().addApiResponse("200", new ApiResponse()
                            .description("OK")
                            .content(new Content().addMediaType("application/json",
                                    new MediaType().schema(genericResponseSchema()))));
                });
            });
        };
    }

    private static Map<String, Schema> schemas() {
        var schemas = new HashMap<String, Schema>();
        schemas.put("GenericResponse", genericResponseSchema());
        return schemas;
    }

    private static Schema<?> genericResponseSchema() {
        return new Schema<>()
                .type("object")
                .addProperty("code", new Schema<>().type("integer").format("int32").example(200))
                .addProperty("message", new Schema<>().type("string").example("OK"))
                .addProperty("data", new Schema<>());
    }
}
