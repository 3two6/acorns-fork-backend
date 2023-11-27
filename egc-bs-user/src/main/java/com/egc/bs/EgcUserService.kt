package com.egc.bs

import com.egc.bs.run.UserInitDataService
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
open class EgcUserService : CommandLineRunner {

    @Autowired
    lateinit var initDataService: UserInitDataService


    @Value("\${data.init}")
    var dataInit: Boolean = false

    @Bean
    open fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info()
                    .title("EasyGrowCrypto API")
                    .description(" Spring Boot RESTful service .")
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
