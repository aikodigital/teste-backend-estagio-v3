package com.gpmrks.testebackendestagiov3.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Teste Estágio Backend V3",
                version = "${api.version}",
                contact = @Contact(
                        name = "Guilherme M.", email = "guilhermepereiramarques@hotmail.com", url = "https://www.linkedin.com/in/guilherme-p-marques/"
                ),
                description = "${api.description}"
        ),
        servers = {@Server(
                url = "${api.server.url}",
                description = "Development"
        )}
)
public class SpringDoc {
}
