package com.egc.bs

import com.egc.bs.run.UserInitDataService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@OpenAPIDefinition(info = Info(
    title = "EasyGrowCrypto Backend API",
    version = "1.0.0-SNAPSHOT",
    description = "Documentation APIs v1.0"
)
)
private open class EgcUserService : CommandLineRunner {

    @Autowired
    lateinit var initDataService: UserInitDataService


    @Value("\${data.init}")
    var dataInit: Boolean = false

    @Bean
    open fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearer-key",
                        SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                    )
            )
    }

    override fun run(vararg args: String?) {
        if (dataInit)
            initDataService.init()
    }
}


fun main(args: Array<String>) {
    configureApplication(SpringApplicationBuilder()).run(*args)
}

fun configureApplication(builder: SpringApplicationBuilder): SpringApplicationBuilder {
    return builder.sources(EgcUserService::class.java)
}
