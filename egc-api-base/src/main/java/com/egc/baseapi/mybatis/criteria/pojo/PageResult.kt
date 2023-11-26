package com.egc.baseapi.mybatis.criteria.pojo

import org.springframework.data.domain.Page

class PageResult<T>() {

    var took:Double?=0.0

    var total=0L//total counts
    var totalPage=0L//total pages
    var items:MutableList<T>?=null //item counts for current page

    constructor(tatal:Long, items:MutableList<T>?):this(){
        this.total=total
        this.items=items
    }

    constructor(total:Long, totalPage:Long, items: MutableList<T>?):this(){
        this.total=total
        this.totalPage=totalPage
        this.items=items
    }

    constructor(total: Long, totalPage: Long, took:Double?, items: MutableList<T>?):this(){
        this.total=total
        this.totalPage=totalPage
        this.items=items
        this.took=took
    }

    fun convertFrom(page:Page<T>):PageResult<T>{
        this.total=page.totalElements
        this.totalPage=page.totalPages.toLong()
        this.items=page.content
        return this
    }

}