package com.egc.bs.entity.message

import com.egc.baseapi.criteria.CommonPageRequest
import com.egc.baseapi.jpa.base.BaseService
import com.egc.baseapi.mybatis.criteria.pojo.PageResult
import com.egc.bs.entity.user.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service


@Service
class MessageService : BaseService<MessageRepository, MessageEntity, RealMessageModel>() {

    override val modelClass: Class<MessageEntity>
        get() = MessageEntity::class.java
    override val realClass: Class<RealMessageModel>
        get() = RealMessageModel::class.java
    override val instance: MessageEntity
        get() = MessageEntity()

    @Autowired
    lateinit var userService: UserService

    override fun getRealModel(primaryModel: MessageEntity?): RealMessageModel? {
        val result = super.getRealModel(primaryModel) as RealMessageModel
        result.userId.run {
            result.user = this?.let {
                userService.retrieveOneWithReal(it)
            }
        }
        return result
    }

    //    change state of message which admin sent into read state
    override fun retrieveByPage(
        commonPageRequest: CommonPageRequest<MessageEntity>,
        auth: Authentication?
    ): PageResult<MessageEntity> {
        val result = super.retrieveByPage(commonPageRequest, auth)
        result.items?.map {
//            if it isnot admin
            if (it.bAdmin == 1.toLong())
                it.bRead = 1
        }
        result.items?.let {
            this.createOrUpdateList(it, null)
        }
        return result
    }

    fun delete(id:Long, auth: Authentication?, request: HttpServletRequest):MessageEntity?{
        val ins=this.retrieveOne(id)
        ins?.bUserDelete=1
        return ins?.let {
            this.createOrUpdateOneWithModel(it,request)
        }
    }


}