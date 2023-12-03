package com.egc.bs.entity.user

import com.egc.baseapi.jpa.user.UserBaseController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/bs_user")
@Tag(name="User")
class UserController:UserBaseController<UserService, UserRepository, UserEntity, RealUserModel>() {

    @Operation(summary = "rest password of user",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @GetMapping("/password_reset/{id}")
    fun resetPassword(@PathVariable("id") id:Long, auth:Authentication?):UserEntity?{
        return this.baseService.resetPassword(id)
    }


    @Operation(summary = "verify",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @PostMapping("verify")
    fun login(request: HttpServletRequest, response: HttpServletResponse, auth: Authentication):RealUserModel{
        return this.baseService.verify(request, response, auth)
    }

    @Operation(summary = "update",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @PostMapping("update")
    fun updateUser(@RequestBody newUser:UserChangeModel, request: HttpServletRequest, response: HttpServletResponse, auth: Authentication):UserEntity?{
        return this.baseService.changeUser(newUser, request, response, auth)
    }



}