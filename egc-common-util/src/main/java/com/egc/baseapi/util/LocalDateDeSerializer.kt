package com.egc.baseapi.util

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.lang.Exception
import java.time.LocalDateTime

class LocalDateDeSerializer:JsonDeserializer<LocalDateTime?>() {

    override fun deserialize(p: JsonParser?, ctxt:DeserializationContext?):LocalDateTime?{
        try {
            val value=p!!.valueAsString
//            val DATEFORMATTER=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//            val ld=LocalDateTime.parse(value, DATETIMEFORMATTER)
//            val ldt=LocalDateTime.of(ld,LocalDateTime.now().toLocalTime())
            return LocalDateTime.parse(value)

        } catch (e:Exception){
            print(e)
            return LocalDateTime.now()

        }
    }

}