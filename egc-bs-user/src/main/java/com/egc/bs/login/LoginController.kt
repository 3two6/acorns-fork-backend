package com.egc.bs.login

import com.egc.bs.entity.user.UserEntity
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user/")
@Tag(name = "Login controller")
class LoginController {

    @Autowired
    lateinit var loginService: LoginService

    @Operation(summary = "login", security = [SecurityRequirement(name = "bearer-key")])
    @PostMapping("login")
    fun login(@RequestBody userInfo: UserEntity, request: HttpServletRequest, response: HttpServletResponse): String? {
        return this.loginService.login(userInfo, request, response);
    }


    @Operation(summary = "register",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @PostMapping("register")
    fun register(
        @RequestBody userInfo: UserEntity,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): UserEntity? {
        return this.loginService.register(userInfo, request, response);
    }

    @Operation(summary = "logout",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @PostMapping("logout")
    fun logout(auth: Authentication) {
//        this.loginService.logout(auth)
    }


}