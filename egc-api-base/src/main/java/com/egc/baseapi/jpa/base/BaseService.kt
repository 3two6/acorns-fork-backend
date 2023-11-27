package com.egc.baseapi.jpa.base

import com.egc.baseapi.criteria.CommonPageRequest
import com.egc.baseapi.mybatis.criteria.pojo.PageResult
import com.egc.baseapi.pojo.BaseModel
import com.egc.baseapi.util.IPUtils
import com.egc.baseapi.util.MyObjectMapper
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.time.LocalDateTime
import java.util.Date

@Service
abstract class BaseService<M : BaseRepository<T>, T : BaseModel, RealModel : T> {

    @Autowired
    lateinit var repository: M

    fun deleteAll() {
        val items = this.repository.findAll()
        for (item in items) {
            this.readyDelete(item)
        }
        this.repository.saveAll(items)
    }

    fun readyDelete(item: T): T {
        item.deleted = true
        item.deletedAt = LocalDateTime.now()
        return item
    }

    open fun deleteByIds(ids: MutableList<Long>) {
        var items = this.repository.findAllById(ids)
        items.forEach { this.readyDelete(it) }
        this.repository.saveAll(items)
    }


    abstract val modelClass: Class<T>
    abstract val realClass: Class<RealModel>
    abstract val instance: T


    @PersistenceContext
    lateinit var entityManager: EntityManager

    open fun retrieveAllWithReal(
        delete: Boolean?,
        auth: Authentication?
    ): MutableList<RealModel> {
        val temp = this.retrieveAll(delete, auth)
        return this.getRealModelList(temp)
    }

    fun getListByIdListWithReal(models: List<Long>): MutableList<RealModel> {
        var result = this.getListByIdList(models)
        return this.getRealModelList(result)
    }

    open fun retrieveByPagewithReal(
        commonPageRequest: CommonPageRequest<T>,
        auth: Authentication?
    ): PageResult<RealModel> {
        val temp = this.retrieveByPage(commonPageRequest, auth)
        var result = PageResult<RealModel>(temp.total, temp.totalPage, mutableListOf())
        temp.items?.run {
            for (item in this) {
                val itemtemp = getRealModel(item)
                if (itemtemp != null)
                    result.items?.add(itemtemp)
            }
        }
        return result
    }


    open fun retrieveAll(delete: Boolean?, auth: org.springframework.security.core.Authentication?): MutableList<T> {
        delete?.let {
            return this.repository.findByDeleted(it)
        }
        return this.repository.findAll()
    }

    fun retrieveOne(id: Long): T? {
        val result = this.repository.findById(id)
        if (result.isPresent) {
            val temp = result.get()
            return if (temp.deleted == true) {
                null
            } else {
                temp
            }
        } else return null
    }


    @Throws(Exception::class)
    open fun createOrUpdateList(models: MutableList<T>, request: HttpServletRequest?): MutableList<T> {
        val result = mutableListOf<T>()
        for (item in models) {
            result.add(this.createOrUpdateOneWithModel(item, request))
        }
        return result
    }


    @Throws(Exception::class)
    open fun createOrUpdateOneWithModel(model: T, request: HttpServletRequest?): T {
        val ip = request?.let { IPUtils.getIpAddr(request) } ?: "modified by coder"
        return if (model.id == null) {
//            create
            model.createdIp = ip
            model.updatedIp = ip
            model.deleted = false
            model.createdAt = LocalDateTime.now()
            model.updatedAt = LocalDateTime.now()
            repository.saveAndFlush(model)
        } else {
//            update
            model.updatedIp = ip
            model.updatedAt = LocalDateTime.now()
            this.repository.saveAndFlush(model)
        }
    }


    open fun retrieveByPage(
        commonPageRequest: CommonPageRequest<T>,
        auth: org.springframework.security.core.Authentication?
    ): PageResult<T> {
        val cb = entityManager.criteriaBuilder
        val dataQuery = cb.createQuery(modelClass)
        val from = dataQuery.from(modelClass)
        val select = dataQuery.select(from)
        val cri: FilterResult = FilterUtil.moreFilter<T>(cb, from, commonPageRequest.more, modelClass)
        cri.predicate?.let {
            select.where(it)
        }

        select.orderBy(cri.orders)
        val temp = entityManager.createQuery(dataQuery)
        temp.maxResults = commonPageRequest.pageSize
        temp.firstResult = (commonPageRequest.pageNo - 1) * commonPageRequest.pageSize
        val count = this.getCount(commonPageRequest) as Long

        return PageResult(count, Math.ceil(count.toDouble() / commonPageRequest.pageSize).toLong(), temp.resultList)
    }


    open fun countByPageRequest(commonPageRequest: CommonPageRequest<T>): Long {
        val cb = entityManager.criteriaBuilder
        val dataQuery = cb.createQuery(modelClass)
        val from = dataQuery.from(modelClass)
        val select = dataQuery.select(from)
        val cri: FilterResult = FilterUtil.moreFilter<T>(cb, from, commonPageRequest.more, modelClass)
        cri.predicate?.let {
            select.where(it)
        }
        return this.getCount(commonPageRequest) as Long
    }




    open fun getCount(commonPageRequest: CommonPageRequest<T>): Long? {
        val cb = entityManager.criteriaBuilder
        val countQuery = cb.createQuery(Long::class.java)
        val from = countQuery.from(modelClass)
        val select = countQuery.select(cb.count(from))
        val cri: FilterResult = FilterUtil.moreFilter<T>(cb, from, commonPageRequest.more, modelClass)
        cri.predicate?.let {
            select.where(it)
        }

        select.orderBy()
        val temp = entityManager.createQuery(countQuery)
        return temp.singleResult
    }


    open fun deleteById(id: Long, request: HttpServletRequest): T? {
        val model = this.retrieveOne(id)
        model?.let {
            this.readyDelete(it)
            this.createOrUpdateOneWithModel(it, request)
            return model
        }
        return null
    }


    @Throws(Exception::class)
    fun saveFile(file: MultipartFile): String {
        val fileName = Date().time.toString() + "_" + file.originalFilename
        val path = String.format("/%s/%s", uploadPath(), fileName)
        val barr = file.bytes
        val bout = BufferedOutputStream(
            FileOutputStream(
                ResourceUtils.getURL("classpath:static/" + uploadPath() + "/").file + fileName
            )
        )
        bout.write(barr)
        bout.flush()
        bout.close()
        return path
    }


    fun getListByIdList(models: List<Long>): MutableList<T> {
        if (models.isEmpty())
            return mutableListOf()
        return this.repository.findAllByIdInAndDeleted(models, false)
    }

    open fun rules(): Map<String, String> {
        return mapOf()
    }

    private fun uploadPath(): String? {
        return null
    }

    open fun getRealModel(primaryModel: T?): RealModel? {
        if (primaryModel === null) return null
        return if (modelClass === realClass)
            primaryModel as RealModel
        else
            MyObjectMapper.convert(primaryModel, realClass)
    }


    protected open fun getRealModelList(primaryModel: MutableList<T>): MutableList<RealModel> {
        val result = mutableListOf<RealModel>()
        for (item in primaryModel) {
            val itemtemp = this.getRealModel(item)
            if (itemtemp !== null)
                result.add(itemtemp)
        }
        return result
    }


    open fun retrieveOneWithReal(id: Long): RealModel? {
        val temp = this.retrieveOne(id)
        return this.getRealModel(temp)
    }



}