package com.egc.bs.entity.admin

import com.egc.bs.entity.action.ActionEntity
import com.egc.bs.entity.permission.PermissionEntity
import com.egc.bs.entity.role.RoleEntity
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description="real model of admin", name="RealAdminModel")
open class RealAdminModel: com.egc.bs.entity.admin.AdminEntity() {

    open var role: com.egc.bs.entity.role.RoleEntity?=null

    open var permissions:MutableList<com.egc.bs.entity.permission.PermissionEntity>?=null
    open var actions:MutableList<com.egc.bs.entity.action.ActionEntity>?=null

}