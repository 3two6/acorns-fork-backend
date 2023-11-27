package com.egc.bs.entity.admin

import com.egc.baseapi.pojo.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Table(name="tb_admin")
@Entity
@Schema(description="AdminEntity",name="tb_admin")
open class AdminEntity:BaseModel() {

    @Schema(description="first name",name="firstName")
    open var firstName:String?=null

    @Schema(description="second name",name="secondName")
    open var secondName:String?=null

    @Schema(description="email of user",name="email")
    open var email:String?=null

    @Schema(description="street address",name="streetAddress")
    open var streetAddress:String?=null

    @Schema(description="social security number",name="ssn")
    open var ssn:String?=null

    @Schema(description="phoneNumber",name="phoneNumber")
    open var phoneNumber:String?=null

    @Schema(description="code of user's role which only a one for each admin",name="roleId")
    open var roleId:Long?=null

    @Schema(description="user's password",name="password")
    open var password:String?=null

    @Schema(description="ip address of user",name="ipAddr")
    open var ipAddr:String?=null

    @Schema(description="platform of user",name="platform")
    open var platform:String?=null

    @Schema(description="whether u a super admin",name="superAdmin")
    open var superAdmin:Int?=null


    companion object{
        fun createOne(email:String?, firstName:String?, secondName:String?,roleCode:Long, userPassword:String): com.egc.bs.entity.admin.AdminEntity {

            val temp= com.egc.bs.entity.admin.AdminEntity()
            temp.email=email
            temp.firstName=firstName
            temp.secondName=secondName
            temp.password=userPassword
            return temp

        }
    }

}