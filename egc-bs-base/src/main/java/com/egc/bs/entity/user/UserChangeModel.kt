package com.egc.bs.entity.user

import io.swagger.v3.oas.annotations.media.Schema


@Schema(description="request model for use change", name="UserChangeModel")
open class UserChangeModel: com.egc.bs.entity.user.UserEntity() {

    open var newPassword:String?=null

}