package com.egc.baseapi.criteria.request

import com.egc.baseapi.criteria.PageRequest
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate


@Schema(name="This is a model used for searching through pages")
class DatePageRequest:PageRequest(),IDateRequest {
    override lateinit var date: LocalDate
}