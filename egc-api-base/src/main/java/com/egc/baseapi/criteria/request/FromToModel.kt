package com.egc.baseapi.criteria.request


import io.swagger.v3.oas.annotations.media.Schema


@Schema(description="Model used when changing data",name="FromToModel")
class FromToModel<T> {
    @Schema(description="from",name="from")
    val from:T?=null
    @Schema(description="to",name="to")
    val to:T?=null
}