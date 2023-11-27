package com.egc.bs.entity.role_action

import com.egc.baseapi.pojo.BaseModel
import com.egc.bs.entity.action.ActionEntity
import com.egc.bs.entity.role.RoleEntity
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Table(name = "tb_role_action")
@Entity
@Schema(description="Bridge relation and action table",name="tb_role_action")
open class RoleActionEntity:BaseModel() {

    @Schema(description="role id",name="role")
    @ManyToOne
    open var role: com.egc.bs.entity.role.RoleEntity?=null

    @Schema(description="name of action",name="action")
    @ManyToOne
    open var action: com.egc.bs.entity.action.ActionEntity?=null

    companion object{
        fun createOne(): com.egc.bs.entity.role_action.RoleActionEntity {
            val temp= com.egc.bs.entity.role_action.RoleActionEntity()
            return temp
        }
    }

}