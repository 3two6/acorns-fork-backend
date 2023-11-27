package com.egc.bs.entity.user

import com.egc.baseapi.pojo.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin


@Table(name="tb_user")
@Entity
@Schema(description="User table", name="tb_user")
open class UserEntity:BaseModel() {

    @Schema(description="firstName", name="firstName")
    open var firstName:String?=null

    @Schema(description="secondName", name="secondName")
    open var secondName:String?=null

    @Schema(description="email", name="email")
    open var email:String?=null

    @Schema(description="streetAddress", name="streetAddress")
    open var streetAddress:String?=null

    @Schema(description="ssn", name="ssn")
    open var ssn:String?=null

    @Schema(description="phoneNumber", name="phoneNumber")
    open var phoneNumber:String?=null

    @Schema(description="roleId", name="roleId")
    open var roleId:Long?=null

    @Schema(description="password", name="password")
    open var password:String?=null

    @Schema(description="ipAddr", name="ipAddr")
    open var ipAddr:String?=null

    @Schema(description="platform", name="platform")
    open var platform:String?=null

    @Schema(description="superAdmin", name="superAdmin")
    open var superAdmin:Int?=null


    companion object{
        fun createOne(email:String?, firstName:String?, secondName:String?,roleCode:Long, userPassword:String): com.egc.bs.entity.user.UserEntity {

            val temp=  UserEntity()
            temp.email=email
            temp.firstName=firstName
            temp.secondName=secondName
            temp.password=userPassword
            return temp

        }
    }

}