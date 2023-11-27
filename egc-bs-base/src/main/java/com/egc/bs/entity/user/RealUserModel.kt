package com.egc.bs.entity.user


import com.egc.bs.entity.user.UserEntity
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description="real model of admin", name="RealUserModel")
open class RealUserModel: com.egc.bs.entity.user.UserEntity() {


}