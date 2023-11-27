package com.egc.bs.entity.notification

import com.egc.baseapi.jpa.base.BaseService
import org.springframework.stereotype.Service


@Service
class NotificationService:BaseService<NotificationRepository, NotificationEntity, NotificationEntity>() {

    override val modelClass: Class<NotificationEntity>
        get() = NotificationEntity::class.java
    override val realClass: Class<NotificationEntity>
        get() = NotificationEntity::class.java
    override val instance: NotificationEntity
        get() = NotificationEntity()



}