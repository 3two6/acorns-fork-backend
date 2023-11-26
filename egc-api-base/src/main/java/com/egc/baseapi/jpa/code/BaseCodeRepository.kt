package com.egc.baseapi.jpa.code

import com.egc.baseapi.jpa.base.BaseRepository
import com.egc.baseapi.mybatis.criteria.pojo.BaseCodeModel
import org.springframework.data.repository.NoRepositoryBean


@NoRepositoryBean
interface BaseCodeRepository<T:BaseCodeModel>:BaseRepository<T> {
    fun findByCode(code:String):T
    fun findAllByParentCodeAndDeleted(parentCode:String, deleted:Boolean):MutableList<T>

    fun findAllByParentCode(parentCode: String):MutableList<T>
}