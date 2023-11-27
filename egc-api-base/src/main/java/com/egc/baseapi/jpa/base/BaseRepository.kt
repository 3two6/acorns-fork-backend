package com.egc.baseapi.jpa.base

import com.egc.baseapi.pojo.BaseModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository


@Repository
interface BaseRepository<T:BaseModel>:JpaRepository<T,Long> {
    fun findByIdAndDeleted(id:Long, deleted:Boolean):T

    fun findByDeleted(deleted: Boolean):MutableList<T>
    fun findAllByIdInAndDeleted(ids:List<Long>, deleted: Boolean):MutableList<T>
}

