package com.egc.baseapi.criteria.request

import java.time.LocalDate

interface IRangeDateRequest {
    var startDate:LocalDate
    var endDate:LocalDate
}