package com.egc.baseapi.criteria


import io.swagger.v3.oas.annotations.media.Schema

@Schema(name="This is a model for researching through pages")
open class PageRequest:IPageRequest {

    @Schema(name="Start from page number 1")
    override var pageNo=1

    @Schema(name="Page size default is 10")
    override var pageSize=10

}