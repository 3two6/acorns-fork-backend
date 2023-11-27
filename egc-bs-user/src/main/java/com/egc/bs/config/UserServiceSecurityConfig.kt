package com.egc.bs.config


import com.egc.bs.service.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
open class UserServiceSecurityConfig {

    //	@Bean: create spring bean and manage by spring ioc container to DI in any place not using keyword "new" anymore
    @Autowired
    private val userDetailsService: UserDetailServiceImpl? = null

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
        val cookieExpiredDate = 7 * 24 * 60 * 60 // 7 days
        http.authorizeHttpRequests(Customizer { auth ->
//            try {
                auth
                    .requestMatchers("/**").permitAll()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
        }).rememberMe { me: RememberMeConfigurer<HttpSecurity?> ->
            me.key(
                "BmKrj8wHOI_1234567890"
            ).tokenValiditySeconds(cookieExpiredDate)
        }
        return http.build()
    }

    @Bean
    open fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers("/images/**", "/**", "/webjars/**")
        }
    }

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//		builder.userDetailsService(userDetailsService)
//				.passwordEncoder(passwordEncoder());
//	}
}
