package com.egc.bs.entity.message

import com.egc.baseapi.pojo.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.aspectj.bridge.Message


@Table(name = "tb_message")
@Entity
@Schema(description="Message table",name="tb_message")
open class MessageEntity:BaseModel() {

    @Schema(description="message_content",name="content")
    open var content:String?=null

    @Schema(description="message_admin null for user send",name="adminId")
    open var adminId:Long?=null

    @Schema(description="message_user",name="userId")
    open var userId:Long?=null

    @Schema(description="reading state 0: unread, 1:read",name="bRead")
    open var bRead:Long?=null

    @Schema(description="0:for user send, 1 for admin send",name="bAdmin")
    open var bAdmin:Long?=null

    @Schema(description="0:user didn't delete msg, 1:delete",name="bUserDelete")
    open var bUserDelete:Int?=null

    @Schema(description="0:admin didn't delete, 1:delete",name="bAdminDelete")
    open var bAdminDelete:Int?=null

//    companion object{
//        fun createOne():MessageEntity{
//            val temp=MessageEntity()
//            return temp
//        }
//    }

}