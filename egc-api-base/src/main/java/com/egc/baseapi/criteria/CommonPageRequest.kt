package com.egc.baseapi.criteria

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern


@Schema(description="Used for additional searches", name="TypeValue")
class TypeValue{
    @Schema(description="Types",allowableValues=["ids,...."], name="type")
    var type:String?=null

    @Schema(description="String value according to the types.",allowableValues=["ids,...."], name="value")
    var value:Any?=null

    @Schema(description="Used for +, -.", name="sort")
    var sort:String?=null
}


@Schema(description="This is a model for researching through pages", name="CommonPageRequest")
class CommonPageRequest<T>{
    @Schema(description="Start from page number 1", name="pageNo")
    var pageNo=1

    @Schema(description="Page size default value is 10", name="pageSize")
    var pageSize=10

    @Schema(description="Additional page model", allowableValues=[""+"{field:{type}}"], name="more")
    var more:Map<String, TypeValue>?=null

    @Schema(description="Search data type.", name="filter")
    var filter:T?=null


    @Schema(description="Operation sort data.", name="sort")
    var sort:@Pattern(regexp="^([+]|[-])[a-zA-Z]+$")String?="-updatedAt"
    var seek=-1

}