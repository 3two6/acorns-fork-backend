package com.egc.baseapi.pojo


import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.time.LocalDateTime


@MappedSuperclass
@Schema(name="It's the basic model of all data bases.")
open class BaseModel {

    @Schema(name="identifier")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    open var id:Long?=null

    @Schema(name="All data is not deleted. set to 1 to be delete")
    @Column(nullable = false)
    open var deleted:Boolean?=null

    @Schema(name="created tie")
    open var createdAt:LocalDateTime?=null

    @Schema(name="created ip")
    @Column(nullable = true)
    open var createdIp:String?=null

    @Schema(name="updated time")
    open var updatedAt:LocalDateTime?=null

    @Schema(name="updated ip")
    @Column(nullable = true)
    open var updatedIp:String?=null

    @Schema(name="deleted time")
    open var deletedAt:LocalDateTime?=null

    @Schema(name="deleted ip")
    @Column(nullable = true)
    open var deletedIp:String?=null


}