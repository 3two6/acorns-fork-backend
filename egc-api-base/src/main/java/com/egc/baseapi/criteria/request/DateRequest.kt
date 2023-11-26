package com.egc.baseapi.criteria.request

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate


@Schema(name="This is a model for searching through pages")
class DateRequest:IDateRequest {
    override lateinit var date:LocalDate
}