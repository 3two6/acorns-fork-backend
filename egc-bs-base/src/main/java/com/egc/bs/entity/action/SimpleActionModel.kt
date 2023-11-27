package com.egc.bs.entity.action


class SimpleActionModel(

    var id:Long?=null,
    var actionName:String?=null,
    var actionUrl:String?=null,
    var actionMethod:String?=null,
    var permissionId:Long?=null

    ){

    constructor(action: com.egc.bs.entity.action.ActionEntity):this(action.id, action.actionName, action.actionUrl, action.actionMethod){

    }

}