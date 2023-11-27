package com.egc.bs.entity.user

import com.egc.baseapi.jpa.base.BaseRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository:BaseRepository<UserEntity> {

    fun findByEmail(email:String):UserEntity

}