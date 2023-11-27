package com.egc.bs.login

import com.egc.bs.entity.user.UserEntity
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user/")
@Tag(name="Login controller")
class LoginController {

    @Autowired
    lateinit var loginService: LoginService

    @Operation(summary = "login")
    @PostMapping("login")
    fun login(@RequestBody userInfo:UserEntity, request: HttpServletRequest, response: HttpServletResponse):String?{
        return this.loginService.login(userInfo, request, response);
    }


    @Operation(summary = "register")
    @PostMapping("register")
    fun register(@RequestBody userInfo:UserEntity, request:HttpServletRequest, response: HttpServletResponse):UserEntity?{
        return this.loginService.register(userInfo, request, response);
    }

    @PostMapping("/logout")
    fun logout(auth:Authentication){
//        this.loginService.logout(auth)
    }


}