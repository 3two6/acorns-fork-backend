package com.egc.baseapi.criteria.request

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description="This is the model for searching through page", name="DateRangeRequest")
open class DateRangeRequest:IRangeDateRequest {
    override lateinit var startDate:LocalDate
    override lateinit var endDate: LocalDate
}