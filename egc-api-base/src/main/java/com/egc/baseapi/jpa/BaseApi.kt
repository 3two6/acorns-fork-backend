package com.egc.baseapi.jpa

import com.egc.baseapi.pojo.BaseModel
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody


interface BaseApi<T:BaseModel>{


    @GetMapping
    @ResponseBody
    open fun GetAll():List<T>

    @GetMapping("/pageall")
    @ResponseBody
    open fun GetAllByPage(): Page<T>?


    @GetMapping("/{id}")
    @ResponseBody
    open fun GetItem(@PathVariable("id")id:Long?):T?


}