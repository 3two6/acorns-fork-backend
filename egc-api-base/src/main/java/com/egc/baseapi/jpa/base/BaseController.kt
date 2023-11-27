package com.egc.baseapi.jpa.base

import com.egc.baseapi.RoleUtil
import com.egc.baseapi.pojo.BaseModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication


abstract class BaseController<S: BaseService<M, T, RealModel>,M: BaseRepository<T>,T:BaseModel,RealModel:T> {

    @Autowired
    lateinit var baseService: S

    open fun getReadRoleList():List<String>?{
        return null
    }

    open fun getWriteRoleList():List<String>?{
        return null
    }

    fun assetReadable(auth: Authentication?){
        this.getReadRoleList()?.let {
            RoleUtil.assertRole(auth,it)
        }
    }

    fun assetWritable(auth: Authentication?){
        this.getWriteRoleList()?.let{
            RoleUtil.assertRole(auth,it)
        }
    }


}