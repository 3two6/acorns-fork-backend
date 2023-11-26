package com.egc.baseapi.jpa.code

import com.egc.baseapi.jpa.base.BaseService
import com.egc.baseapi.mybatis.criteria.pojo.BaseCodeModel
import jakarta.servlet.http.HttpServletRequest
import java.lang.Exception

abstract class BaseCodeService<M : BaseCodeRepository<T>, T : BaseCodeModel, RealModel : T> :
    BaseService<M, T, RealModel>() {


    private fun format2String(str:String):String{
        if (str.length>1) return str
        return "0$str"
    }
    fun getCodeOf(model: T): String? {
        try {
            val children = model.parentCode?.let {
                this.repository.findAllByParentCode(it)
            }
            var maxCode = 1
            children?.run {
                this.map {
                    val arr = it.code?.split(".")//C.01.02.01
                    val code = arr?.get(arr.size - 1)?.toInt()
                    if (code != null) {
                        if (maxCode < code) maxCode = code
                    }
                }
            }
            return model.parentCode + "." + format2String((maxCode + 1).toString())
        } catch (e: Exception) {
            return ""
        }
    }


    fun orderUp(model: T, request: HttpServletRequest): MutableList<T>? {
        val companies = model.parentCode?.let {
            this.repository.findAllByParentCodeAndDeleted(it, false)
        }
        try {
            val result = companies?.filter { it -> it.orderCode!! < model.orderCode.toString() }
            if (result == null || result.size == 0) {
                return null
            }
            val sortedArr = result.sortedByDescending { (it.orderCode) }
            val otherModel = sortedArr.get(0)
            val tmp = otherModel.orderCode
            otherModel.orderCode = model.orderCode
            model.orderCode = tmp
            val willCreates = mutableListOf<T>()
            willCreates.add(model)
            willCreates.add(otherModel)
            return this.createOrUpdateList(willCreates, request)
        } catch (e: Exception) {
            print(e)
        }
        return null
    }


    fun orderDown(model: T, request: HttpServletRequest): MutableList<T>? {
        val companies = model.parentCode?.let {
            this.repository.findAllByParentCodeAndDeleted(it, false)
        }
        val result = companies?.filter { it ->
            it.orderCode!! > model.orderCode.toString()
        }
        if (result == null || result.size == 0) {
            return null
        }
        val sortedArr = result.sortedBy{  it.orderCode  }

        val otherModel = sortedArr.get(0)
        val tmp = otherModel.orderCode
        otherModel.orderCode = model.orderCode
        model.orderCode = tmp
        val willCreats = mutableListOf<T>()
        willCreats.add(model)
        willCreats.add(otherModel)
        return this.createOrUpdateList(willCreats, request)
    }


    fun retrieveOneWithCode(code: String): T? {
        try {
            return this.repository.findByCode(code)
        } catch (e: Exception) {
            return null
        }
    }

    //    delete all codes whose parent is ur interest
    override fun deleteById(id: Long, request: HttpServletRequest): T? {
        val codeIns = this.repository.findById(id)
        val dellds = mutableListOf<Long>()
        if (codeIns.isPresent) {
            val code = codeIns.get().code
            val delTs = code?.let {
                this.repository.findAllByParentCodeAndDeleted(it, false)
            }
            delTs?.map { it.id?.let { it1 -> dellds.add(it1) } }
        }
        super.deleteByIds(dellds)
        return super.deleteById(id, request)
    }



}