package com.egc.baseapi.pojo


import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.time.LocalDateTime


@MappedSuperclass
@Schema(description="It's the basic model of all data bases.", name="BaseModel")
open class BaseModel {

    @Schema(description="identifier", name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    open var id:Long?=null

    @Schema(description ="All data is not deleted. set to 1 to be delete", name = "deleted")
    @Column(nullable = false)
    open var deleted:Boolean?=null

    @Schema(description="created time" ,name="createdAt")
    open var createdAt:LocalDateTime?=null

    @Schema(description ="created ip", name="createdIp")
    @Column(nullable = true)
    open var createdIp:String?=null

    @Schema(description="updated time", name="updatedAt" )
    open var updatedAt:LocalDateTime?=null

    @Schema(description="updated ip", name="updatedIp" )
    @Column(nullable = true)
    open var updatedIp:String?=null

    @Schema(description="deleted time", name="deletedAt" )
    open var deletedAt:LocalDateTime?=null

    @Schema(description="deleted ip", name="deletedIp" )
    @Column(nullable = true)
    open var deletedIp:String?=null


}