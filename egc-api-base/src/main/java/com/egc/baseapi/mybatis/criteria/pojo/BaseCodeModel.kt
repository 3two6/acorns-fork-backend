package com.egc.baseapi.mybatis.criteria.pojo

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass


@MappedSuperclass
open class BaseCodeModel:CategoryModel() {
    @Column(nullable = false)
    open var code:String?=null
    open var name:String?=null
    open var parentCode:String?=null
    open var orderCode:String?=null

    companion object{

        fun createOne(code:String, name:String):BaseCodeModel{
            var tmp=BaseCodeModel()
            tmp.code=code
            tmp.name=name
            return tmp
        }

    }

}