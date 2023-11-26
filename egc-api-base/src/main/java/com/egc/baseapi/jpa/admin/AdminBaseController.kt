package com.egc.baseapi.jpa.admin

import com.egc.baseapi.RoleUtil
import com.egc.baseapi.criteria.CommonPageRequest
import com.egc.baseapi.jpa.base.BaseController
import com.egc.baseapi.jpa.base.BaseRepository
import com.egc.baseapi.jpa.base.BaseService
import com.egc.baseapi.mybatis.criteria.pojo.PageResult
import com.egc.baseapi.pojo.BaseModel
import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.lang.Exception

 abstract class AdminBaseController<S : BaseService<M, T, RealModel>, M : BaseRepository<T>, T : BaseModel, RealModel : T> :
    BaseController<S, M, T, RealModel>() {

    /*
    Retrieve all list
    @return All entity list
    */
    @Operation(summary ="all list")
    @GetMapping("/list")
    open fun retrieveAll(
        @RequestParam(value = "delete", required = false, defaultValue = "false") delete: Boolean?,
        auth: Authentication?
    ): List<T> {
        this.getReadRoleList()?.let {
            RoleUtil.assertRole(auth, it)
        }
        return this.baseService.retrieveAll(delete, auth)
    }


    /*
    Retrieve one entity
    @param id unique identifier
    @return  entity
*/
    @Operation(summary ="retrieve one entity")
    @GetMapping("/{id}")
    fun retrieveOne(@PathVariable("id") id: Long, auth: Authentication?): T? {
        this.getReadRoleList()?.let {
            RoleUtil.assertRole(auth, it)
        }
        return this.baseService.retrieveOne(id)
    }

    /*
    add and update
    @param model entity u wanna add and edit
    @return Unique identification|Success or not
    */
    @Operation(summary ="add and update entity")
    @PostMapping
    @Throws(Exception::class)
    open fun createOrUpdateOne(@RequestBody model: T, request: HttpServletRequest, auth: Authentication?): T {
        this.getWriteRoleList()?.let {
            RoleUtil.assertRole(auth, it)
        }
        return this.baseService.createOrUpdateOneWithModel(model, request)
    }


    /*
    retrieve entity by page
    @parma commonPageRequest pageInfo
    @return pageable entity
    */
    @Operation(summary ="retrieve entity by page")
    @PostMapping("/page")
    open fun retrieveByPage(@RequestBody commonPageRequest: CommonPageRequest<T>, auth:Authentication?):PageResult<T>{
        this.getReadRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        return this.baseService.retrieveByPage(commonPageRequest,auth)
    }



    @Operation(summary ="updates and additions are carried out in array. u can also mix update and additions")
    @PostMapping("/batch")
    fun createOrUpdateModels(@RequestBody models:MutableList<T>, request: HttpServletRequest,auth:Authentication?):List<T>{
        this.getWriteRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        return this.baseService.createOrUpdateList(models,request)
    }

    /*
    delete
    @param id Unique identifier ot the entity to be deleted
    @return success or failure
*/
    @Operation(summary ="delete")
    @DeleteMapping("/{id}")
    open fun deleteOne(@PathVariable("id")id:Long, auth:Authentication?,request: HttpServletRequest):T?{
        this.getWriteRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }

        return this.baseService.deleteById(id,request)
    }


    @Operation(summary ="retrieve entities by ids")
    @PostMapping("/ids")
    fun getByIds(@RequestBody ids:List<Long>,auth:Authentication?):List<T>{
        this.getReadRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        return this.baseService.getListByIdList(ids)
    }



    @Operation(summary ="delete all entities")
    @DeleteMapping("/all")
    fun deleteAll(auth:Authentication?){
        this.getWriteRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        this.baseService.deleteAll()
    }

    /*
    * 식별자에 의한 삭제
    * */
    @Operation(summary ="retrieve entity by id")
    @DeleteMapping("/ids")
    open fun deleteIds(@RequestBody ids:MutableList<Long>, auth:Authentication?){
        this.getWriteRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        this.baseService.deleteByIds(ids)
    }


    @Operation(summary ="retrieve all entities with real ")
    @GetMapping("/listreal")
    fun retrieveAllWithReal(@RequestParam(value = "delete", required = false, defaultValue = "0")delete: Boolean?,auth: Authentication):List<T>{
        this.getReadRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        return baseService.retrieveAllWithReal(delete,auth)
    }


    @Operation(summary ="retrieve all entities by page with real")
    @PostMapping("/pagereal")
    open fun retrieveByPagewithReal(@RequestBody commonPageRequest: CommonPageRequest<T>,auth: Authentication?):PageResult<RealModel>{
        this.getReadRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        return baseService.retrieveByPagewithReal(commonPageRequest,auth)
    }


    @Operation(summary ="retrieve entity by id")
    @GetMapping("/real/{id}")
    fun retrieveByOne(@PathVariable("id")id:Long, auth: Authentication?):RealModel?{
        this.getReadRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
        return baseService.retrieveOneWithReal(id)
    }


}
