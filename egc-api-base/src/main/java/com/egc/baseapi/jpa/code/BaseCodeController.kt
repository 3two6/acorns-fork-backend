package com.egc.baseapi.jpa.code

import com.egc.baseapi.jpa.admin.AdminBaseController
import com.egc.baseapi.mybatis.criteria.pojo.BaseCodeModel
import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody


abstract class BaseCodeController<S:BaseCodeService<M, T, RealModel>, M:BaseCodeRepository<T>,T:BaseCodeModel, RealModel:T>:
    AdminBaseController<S, M, T, RealModel>() {

    @PostMapping("/add_code")
    @ResponseBody
    @Operation(summary ="add code",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    fun addCategory(@RequestBody model:T, request: HttpServletRequest, auth:Authentication):T{
        model.code=this.baseService.getCodeOf(model)
        model.orderCode= model.hashCode().toString()
        return super.createOrUpdateOne(model, request, auth)
            }


    @PostMapping("/order_up")
    @ResponseBody
    @Operation(summary ="order code up",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    fun orderUp(@RequestBody model: T, request: HttpServletRequest, auth: Authentication):MutableList<T>?{
        return this.baseService.orderUp(model, request)
    }


    @PostMapping("/order_down")
    @ResponseBody
    @Operation(summary ="down category",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    fun orderDown(@RequestBody model: T,request: HttpServletRequest, auth: Authentication):MutableList<T>?{
        return this.baseService.orderDown(model, request)
    }


}