package com.egc.bs.config


import com.egc.bs.filter.AuthenticationFilter
import com.egc.bs.filter.JWTAuthenticationFilter
import com.egc.bs.service.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.util.AntPathMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
open class UserServiceSecurityConfig {

    //	@Bean: create spring bean and manage by spring ioc container to DI in any place not using keyword "new" anymore
    @Autowired
    private val userDetailsService: UserDetailServiceImpl? = null

    @Autowired
    lateinit var authenticationFilter: AuthenticationFilter

    private val jwtAuthenticationFilter: JWTAuthenticationFilter = JWTAuthenticationFilter()

    private val ACTUATOR_WHITELIST: Array<String> = arrayOf("")

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    open fun authenticationManager(): AuthenticationManager {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return ProviderManager(authProvider)
    }

    @Bean
    @Throws(Exception::class)
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        val cookieExpiredDate = 7 * 24 * 60 * 60 // 7 days


        http.authorizeHttpRequests { auth ->
            auth.requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/user/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/logout").permitAll().anyRequest().authenticated()
        }
//            .cors { config ->
//                config.configurationSource(UrlBasedCorsConfigurationSource().also { cors ->
//                    CorsConfiguration().apply {
//                        allowedOrigins = listOf("http://localhost:8080")
//                        allowedMethods = listOf("POST", "PUT", "DELETE", "GET", "OPTIONS", "HEAD")
//                        allowedHeaders = listOf(
//                            "Authorization",
//                            "Content-Type",
//                            "X-Requested-With",
//                            "Accept",
//                            "Origin",
//                            "Access-Control-Request-Method",
//                            "Access-Control-Request-Headers"
//                        )
//                        exposedHeaders = listOf(
//                            "Access-Control-Allow-Origin",
//                            "Access-Control-Allow-Credentials",
//                            "Authorization",
//                            "Content-Disposition"
//                        )
//                        maxAge = 3600
//                        cors.registerCorsConfiguration("http://localhost:8080", this)
//
//                    }
//                })
//            }
//            .rememberMe { me -> me.key("BmKrj8wHOI_1234567890") }.csrf { csrf -> csrf.disable() }
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
//            .addFilter(jwtAuthenticationFilter)


        val httpRequestCache = HttpSessionRequestCache()

        http.requestCache { cache -> cache.requestCache(httpRequestCache) }

        return http.build()
    }


    @Bean
    open fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers("/swagger-ui/**", "/v3/**", "/swagger-ui.html","/user/**")
        }
    }

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//		builder.userDetailsService(userDetailsService)
//				.passwordEncoder(passwordEncoder());
//	}
}
