package com.egc.baseapi.criteria.request

import jakarta.validation.constraints.Pattern


class SimplePageRequest<T> {
    private var pageNo=1
    private var pageSize=10
    var filter:List<T>?=null
    var sort:@Pattern(regexp = "^([+]|[-])[a-zA-Z]+$")String?="-updatedAt"

    var seek=-1
        get() = if(field==-1)(pageNo-1)*pageSize else  field

    fun getPageNo():Int{
        return pageNo
    }

    fun setPageNo(pageNo:Int?){
        this.pageNo=Math.max(pageNo!!,1)
    }

    fun getPageSize():Int{
        return pageSize
    }
    fun setPageSize(pageSize:Int){
        this.pageSize=if (pageSize<0)10 else pageSize
    }
}