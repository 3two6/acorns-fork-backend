package com.egc.bs.entity.message

import com.egc.bs.entity.admin.AdminEntity
import com.egc.bs.entity.user.UserEntity

open class RealMessageModel: com.egc.bs.entity.message.MessageEntity() {

    open var user: com.egc.bs.entity.user.UserEntity?=null
    open var admin: com.egc.bs.entity.admin.AdminEntity?=null

}