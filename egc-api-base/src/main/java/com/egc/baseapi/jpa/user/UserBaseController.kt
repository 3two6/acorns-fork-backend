package com.egc.baseapi.jpa.user

import com.egc.baseapi.RoleUtil
import com.egc.baseapi.criteria.CommonPageRequest
import com.egc.baseapi.jpa.base.BaseController
import com.egc.baseapi.jpa.base.BaseRepository
import com.egc.baseapi.jpa.base.BaseService
import com.egc.baseapi.mybatis.criteria.pojo.PageResult
import com.egc.baseapi.pojo.BaseModel
import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

abstract class UserBaseController<S:BaseService<M,T, RealModel>, M:BaseRepository<T>,T:BaseModel, RealModel:T>:BaseController<S, M,T,RealModel>() {
    /*
    * retrieve all entities
    * @return all entities
    * */
    @Operation(summary ="all entities",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @GetMapping("/list")
    open fun retrieveAll(@RequestParam(value = "delete", required = false, defaultValue = "false")delete: Boolean, auth:Authentication?):List<T>{
        this.getReadRoleList()?.let {
            RoleUtil.assertRole(auth,it)
        }
        return this.baseService.retrieveAll(delete, auth)
    }

    /*
    * retrieve entity by id
    * @parma id unique identifier
    * @return entity
    * */
    @Operation(summary ="retrieve entity by id",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @GetMapping("{id}")
    fun retrieveOne(@PathVariable("id")id:Long, auth: Authentication?):T?{
        this.getReadRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        return this.baseService.retrieveOne(id)
    }


    /*
    * retrieve entities by page
    * @param commonPageRequest pageInfo
    * @return pageable entity list
    * */
    @Operation(summary ="retrieve entities by page",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @PostMapping("/page")
    open fun retrieveByPage(@RequestBody commonPageRequest: CommonPageRequest<T>, auth: Authentication?):PageResult<T>{
        this.getReadRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        return this.baseService.retrieveByPage(commonPageRequest,auth)
    }


    @Operation(summary ="retrieve entities by ids",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @PostMapping("/ids")
    fun getByIds(@RequestBody ids:List<Long>, auth: Authentication?):List<T>{
        this.getReadRoleList()?.let {
            RoleUtil.assertRole(auth,it)
        }
        return this.baseService.getListByIdList(ids)
    }


    @Operation(summary ="retrieve all entities with real",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @GetMapping("/listreal")
    fun retrieveAllWithReal(@RequestParam(value = "delete", required = false, defaultValue = "0")delete: Boolean?,auth: Authentication?):List<T>{
        this.getReadRoleList()?.let {
            RoleUtil.assertRole(auth,it)
        }

        return baseService.retrieveAllWithReal(delete, auth)
    }


    @Operation(summary ="retrieve entities by page with real",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @PostMapping("/pagereal")
    open fun retrieveByPagewithRealModel(@RequestBody commonPageRequest: CommonPageRequest<T>, auth: Authentication?):PageResult<RealModel>{
        this.getReadRoleList()?.let {
            RoleUtil.assertRole(auth,it)
        }
        return baseService.retrieveByPagewithReal(commonPageRequest, auth)
    }


    @Operation(summary ="retrieve entity by id with real",security = [io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")])
    @GetMapping("/real/{id}")
    fun retrieveByOne(@PathVariable("id") id:Long, auth: Authentication):RealModel?{
        this.getReadRoleList()?.let {
            RoleUtil.assertRole(auth,it)
        }
        return baseService.retrieveOneWithReal(id)
    }







}