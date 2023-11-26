package com.egc.baseapi.jpa.admin

import com.egc.baseapi.jpa.base.BaseRepository
import com.egc.baseapi.pojo.BaseModel
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository


@NoRepositoryBean
@Repository
interface AdminBaseRepository<T:BaseModel>: BaseRepository<T> {

}