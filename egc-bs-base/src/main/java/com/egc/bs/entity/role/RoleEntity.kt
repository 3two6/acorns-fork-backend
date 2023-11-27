package com.egc.bs.entity.role

import com.egc.baseapi.pojo.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.Table
import javax.management.relation.Role


@Table(name = "tb_role")
@Entity
@Schema(description="Role table", name="tb_role")
open class RoleEntity:BaseModel() {


    @Schema(description="role name", name="name")
    open var name:String?=null

    companion object{

        fun createOne(): com.egc.bs.entity.role.RoleEntity {
            val temp= com.egc.bs.entity.role.RoleEntity()
            return temp
        }

    }

}