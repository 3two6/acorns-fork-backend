package com.egc.baseapi.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeSerializer: JsonSerializer<LocalDateTime?>() {
    override fun serialize(value:LocalDateTime?, gen:JsonGenerator?, serializers:SerializerProvider?){
        if (value!=null){
            gen?.writeString(value.format(DateTimeFormatter.ISO_DATE_TIME))
        }
    }
}