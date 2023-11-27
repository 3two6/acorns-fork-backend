package com.egc.bs.entity.permission

import com.egc.baseapi.pojo.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Table(name = "tb_permission")
@Entity
@Schema(description="Permission table", name="tb_permission")
open class PermissionEntity:BaseModel() {

    @Schema(description="name of page", name="pageName")
    open var pageName:String?=null

    @Schema(description="url of page", name="pageUrl")
    open var pageUrl:String?=null

    companion object{

        fun createOne(pageName:String?, pageUrl:String?): com.egc.bs.entity.permission.PermissionEntity {
            val temp= com.egc.bs.entity.permission.PermissionEntity()
            temp.pageName=pageName
            temp.pageUrl=pageUrl
            return temp
        }

    }

}