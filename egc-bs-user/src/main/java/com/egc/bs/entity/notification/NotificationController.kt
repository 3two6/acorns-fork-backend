package com.egc.bs.entity.notification

import com.egc.baseapi.jpa.user.UserBaseController
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/bs_notification")
@Tag(name="notificaion")
class NotificationController:UserBaseController<NotificationService, NotificationRepository, NotificationEntity, NotificationEntity>() {



}