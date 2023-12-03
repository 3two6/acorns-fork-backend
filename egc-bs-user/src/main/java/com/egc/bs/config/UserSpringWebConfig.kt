package com.egc.bs.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class UserSpringWebConfig: WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedMethods("HEAD","GET","PUT", "POST", "DELETE","PATCH")
            .allowedHeaders("*")
            .exposedHeaders("Accept-Ranges", "Content-Encoding", "Content-Length", "Content-Type")
    }
}