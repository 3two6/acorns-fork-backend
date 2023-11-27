package com.egc.bs.entity.role_permission

import com.egc.baseapi.pojo.BaseModel
import com.egc.bs.entity.permission.PermissionEntity
import com.egc.bs.entity.role.RoleEntity
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Table(name = "tb_role_permission")
@Entity
@Schema(description="Relation role and permission table",name="tb_role_permission")
open class RolePermissionEntity:BaseModel() {

    @Schema(description="role id",name="role")
    @ManyToOne
    open var role: com.egc.bs.entity.role.RoleEntity?=null

    @Schema(description="name of permission",name="permission")
    @ManyToOne
    open var permission: com.egc.bs.entity.permission.PermissionEntity?=null

    companion object{

        fun createOne(): com.egc.bs.entity.role_permission.RolePermissionEntity {
            val temp= com.egc.bs.entity.role_permission.RolePermissionEntity()
            return temp
        }

    }

}