package com.egc.bs.entity.action

import com.egc.baseapi.pojo.BaseModel
import com.egc.bs.entity.permission.PermissionEntity
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Table(name = "tb_action")
@Entity
@Schema(description="Action table",name="ActionEntity")
open class ActionEntity:BaseModel(){

    @Schema(description = "name of action", name="actionName")
    open var actionName:String?=null

    @Schema(description="request url of action", name="actionUrl")
    open var actionUrl:String?=null

    @Schema(description="permission id", name="permission")
    @ManyToOne
    open var permission: com.egc.bs.entity.permission.PermissionEntity?=null

    @Schema(description="action method", name="actionMethod")
    open var actionMethod:String?=null


    companion object{
        fun createOne(actionName:String?, actionUrl:String?, permissionId:Long?): com.egc.bs.entity.action.ActionEntity {
            val temp= com.egc.bs.entity.action.ActionEntity()
            temp.actionName=actionName
            temp.actionUrl=actionUrl
            return temp
        }
    }



}