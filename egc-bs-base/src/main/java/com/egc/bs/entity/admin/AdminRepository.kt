package com.egc.bs.entity.admin

import com.egc.baseapi.jpa.base.BaseRepository
import org.springframework.stereotype.Repository


@Repository
interface AdminRepository:BaseRepository<com.egc.bs.entity.admin.AdminEntity> {
    fun findByEmailAndDeleted(email:String, deleted:Boolean):MutableList<com.egc.bs.entity.admin.AdminEntity>

}