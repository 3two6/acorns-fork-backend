package com.egc.baseapi.mybatis.criteria

import jakarta.validation.constraints.Pattern
import kotlin.math.max

//@Deprecated("")
class SearchPageRequest<T> {
    private var pageNo = 1
    private var pageSize = 10
    var filter: T? = null

//        private set
    var sort: @Pattern(regexp = "^([+]|[-])[a-zA-Z]+$") String? = "-updatedDt"
    val seek: Int
        get() = (pageNo - 1) * pageSize

    fun setFilters(filter: T?) {
        this.filter = filter
    }

    fun getPageNo(): Int {
        return pageNo
    }

    fun setPageNo(pageNo: Int) {
        this.pageNo = max(pageNo.toDouble(), 1.0).toInt()
    }

    fun getPageSize(): Int {
        return pageSize
    }

    fun setPageSize(pageSize: Int) {
        this.pageSize = if (pageSize < 0) 10 else pageSize
    }
}