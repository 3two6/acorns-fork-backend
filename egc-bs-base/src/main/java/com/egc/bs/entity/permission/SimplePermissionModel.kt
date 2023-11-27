package com.egc.bs.entity.permission


class SimplePermissionModel(

    var id:Long?=null,
    var pageName:String?=null,
    var pageUrl:String?=null

) {

    constructor(permission: com.egc.bs.entity.permission.PermissionEntity):this(permission.id, permission.pageName, permission.pageUrl){

    }

}