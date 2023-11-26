package com.egc.baseapi.mybatis.criteria.pojo

import com.egc.baseapi.pojo.BaseModel
import jakarta.persistence.MappedSuperclass


@MappedSuperclass
open class CategoryModel:BaseModel() {

    open var dispOrder:Int?=null

}