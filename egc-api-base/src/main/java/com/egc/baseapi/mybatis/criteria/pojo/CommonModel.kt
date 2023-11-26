package com.egc.baseapi.mybatis.criteria.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.format.annotation.DateTimeFormat
import java.util.*


@MappedSuperclass
open class CommonModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int?=null,

    var isDelete:Int?=null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createDt: Date?=null,
    var createdIp:String?=null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedDt: Date?=null,
    var updatedIp:String?=null,


){}