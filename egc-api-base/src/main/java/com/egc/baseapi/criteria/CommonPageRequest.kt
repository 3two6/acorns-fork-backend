package com.egc.baseapi.criteria

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern


@Schema(name="Used for additional searches")
class TypeValue{
    @Schema(name="Types",allowableValues=["ids,...."])
    var type:String?=null

    @Schema(name="String value according to the types.",allowableValues=["ids,...."])
    var value:Any?=null

    @Schema(name="Used for +, -.")
    var sort:String?=null
}


@Schema(name="This is a model for researching through pages")
class CommonPageRequest<T>{
    @Schema(name="Start from page number 1")
    var pageNo=1

    @Schema(name="Page size default value is 10")
    var pageSize=10

    @Schema(name="Additional page model", allowableValues=[""+"{field:{type}}"])
    var more:Map<String, TypeValue>?=null

    @Schema(name="Search data type.")
    var filter:T?=null

    var sort:@Pattern(regexp="^([+]|[-])[a-zA-Z]+$")String?="-updatedAt"
    var seek=-1

}