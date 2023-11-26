package com.egc.baseapi.criteria.request


import io.swagger.v3.oas.annotations.media.Schema


@Schema(name="Model used when changing data")
class FromToModel<T> {
    @Schema(name="from")
    val from:T?=null
    @Schema(name="to")
    val to:T?=null
}