package com.egc.baseapi.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import jdk.internal.org.objectweb.asm.TypeReference
import org.slf4j.LoggerFactory
import org.springframework.lang.Nullable
import java.io.IOException
import java.lang.reflect.Type
import java.time.LocalDateTime

object JsonUtils {
    val mapper = ObjectMapper()
    private val logger = LoggerFactory.getLogger(JsonUtils::class.java)
    @Nullable
    fun serialize(obj: Any?): String? {
        val module = SimpleModule()
        module.addSerializer(LocalDateTime::class.java, DateTimeSerializer())
        val mapper = ObjectMapper()
        mapper.registerModule(module)
        if (obj == null) {
            return null
        }
        return if (obj.javaClass == String::class.java) {
            obj as String?
        } else try {
            mapper.writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            logger.error("json serialize error:$obj", e)
            null
        }
    }

    @Nullable
    fun <T> parse(json: String, tClass: Class<T>?): T? {
        return try {
            mapper.readValue(json, tClass)
        } catch (e: IOException) {
            logger.error("json analyze error$json", e)
            null
        }
    }

    @Nullable
    fun <E> parseList(json: String, eClass: Class<E>?): List<E>? {
        return try {
            mapper.readValue(
                json, mapper.typeFactory.constructCollectionType(
                    MutableList::class.java, eClass
                )
            )
        } catch (e: IOException) {
            logger.error("json analyze error$json", e)
            null
        }
    }

    @Nullable
    fun <K, V> parseMap(json: String, kClass: Class<K>?, vClass: Class<V>?): Map<K, V>? {
        return try {
            mapper.readValue(
                json, mapper.typeFactory.constructMapType(
                    MutableMap::class.java, kClass, vClass
                )
            )
        } catch (e: IOException) {
            logger.error("json analyze error$json", e)
            null
        }
    }

    @Nullable
    fun <T> nativeRead(json: String, type: TypeReference?): T? {
        return try {
            mapper.readValue(json, mapper.typeFactory.constructType(type as Type?))
        } catch (e: IOException) {
            logger.error("json analyze error$json", e)
            null
        }
    }

    @Nullable
    fun <T> convertValue(obj: Any, tClass: Class<T>?): T? {
        return try {
            val module = SimpleModule()
            //            module.addSerializer(LocalDate.class, new LocaleDateSerializer)
            module.addDeserializer(LocalDateTime::class.java, LocalDateDeSerializer())
            val mapper = ObjectMapper()
            mapper.registerModule(module)
            mapper.convertValue(obj, tClass)
        } catch (e: Exception) {
            logger.error("json analyze error$obj", e)
            null
        }
    }
}
