package com.egc.bs.entity.message

import com.egc.baseapi.jpa.base.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository:BaseRepository<com.egc.bs.entity.message.MessageEntity> {

    @Query("select p from MessageEntity p where p.bAdmin=?1 and p.bAdminDelete=?2 and p.deleted=false ")
    fun findAllAdminReceived(bAdmin:Long, bAdminDelete: Int, pageable: Pageable): Page<com.egc.bs.entity.message.MessageEntity>

    @Query("select p from MessageEntity p where (p.userId=?1 and ( p.bAdmin=?1 and p.adminId=?2 or p.bAdmin=0) and p.bAdminDelete=0 and p.deleted=?3)")
    fun findAllByAdminUserMessage(userId:Long?, adminId: Long?,deleted:Boolean, pageable: Pageable): Page<com.egc.bs.entity.message.MessageEntity>



}