package com.egc.bs.entity.role

class SimpleRoleModel(
    id:Long?=null,
    name:String?=null
) {

    constructor(role: com.egc.bs.entity.role.RoleEntity?):this(role?.id, role?.name){

    }

}