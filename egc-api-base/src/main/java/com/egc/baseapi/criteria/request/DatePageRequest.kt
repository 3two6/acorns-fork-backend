package com.egc.baseapi.criteria.request

import com.egc.baseapi.criteria.PageRequest
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate


@Schema(description="This is a model used for searching through pages", name="DatePageRequest")
class DatePageRequest:PageRequest(),IDateRequest {
    override lateinit var date: LocalDate
}