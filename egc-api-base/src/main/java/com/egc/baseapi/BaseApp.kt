package com.egc.baseapi

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner



abstract class BaseApp:CommandLineRunner{

    @Value("\${spring.application.name}")
    var name:String?=null

    @Value("\${spring.application.alias: name that manager can understand}")
    var alias:String?=null

    @Value("\${spring.application.description: system description}")
    var description:String?=null

    override fun run(vararg  args:String?){
//        val service=ServiceEntity()
//        service.name=this.name
//        service.alias=this.alias
//        service.description=this.description
//        try {
//            this.serviceRegisterApi?.createService(service)
//        }catch (e:Exception){
//            e.pringStackTrace()
//        }
    }

}