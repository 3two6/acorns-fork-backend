package com.egc.bs.entity.message

import com.egc.baseapi.jpa.user.UserBaseController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/bs_message")
@Tag(name="manage message")
class MessageController:UserBaseController<MessageService, MessageRepository, MessageEntity, RealMessageModel>() {

    @Operation(summary = "Creat and update",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @PostMapping
    @Throws(Exception::class)
    fun createOrUpdateOne(@RequestBody model:MessageEntity, request: HttpServletRequest, auth:Authentication?):MessageEntity{
        model.bUserDelete=0
        model.bAdminDelete=0
        return this.baseService.createOrUpdateOneWithModel(model,request)
    }

    @Operation(summary = "Delete",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable("id") id:Long, auth: Authentication?, request: HttpServletRequest):MessageEntity?{
        return this.baseService.delete(id, auth, request)
    }


}