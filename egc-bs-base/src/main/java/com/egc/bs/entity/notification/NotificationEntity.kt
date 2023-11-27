package com.egc.bs.entity.notification

import com.egc.baseapi.pojo.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.Table


@Table(name="tb_notification")
@Entity
@Schema(description="Notification table",name="tb_notification")
open class NotificationEntity:BaseModel() {

    @Schema(description="title of notification",name="title")
    open var title:String?=null

    @Schema(description="content of notification",name="content")
    open var content:String?=null

    companion object{

        fun createOne(pageName:String?, pageUrl:String?): com.egc.bs.entity.notification.NotificationEntity {
            val temp= com.egc.bs.entity.notification.NotificationEntity()

            return temp
        }

    }

}