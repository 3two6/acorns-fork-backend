package com.egc.baseapi.jpa.base


import com.egc.baseapi.criteria.TypeValue
import com.egc.baseapi.pojo.BaseModel
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Order
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import java.lang.Exception
import java.lang.reflect.Field
import java.time.LocalDate
import java.time.LocalDateTime


class FilterUtil {
    companion object{
        fun<T> makePredicate(path: String, type:String?, searchValue:Any, criteriaBuilder: CriteriaBuilder, from: Root<T>, field:Field): Any? {
            when(field.type){
                Integer::class.java->{
                    return when(type){
                        null->{
                            criteriaBuilder.equal(from.get<Int>(path), searchValue as Int)
                        }
                        "equal"->{
                            criteriaBuilder.equal(from.get<Int>(path), searchValue as Int)
                        }
                        ">"->{
                            criteriaBuilder.greaterThan(from.get<Int>(path), searchValue as Int)
                        }
                        "<"->{
                            criteriaBuilder.lessThan(from.get<Int>(path), searchValue as Int)
                        }
                        ">="->{
                            criteriaBuilder.greaterThanOrEqualTo(from.get<Int>(path), searchValue as Int)
                        }
                        "<="->{
                            criteriaBuilder.lessThanOrEqualTo(from.get<Int>(path), searchValue as Int)
                        }
                        "ids"->{
                            val value=searchValue as String
                            val ranges=value.split(",")
                            val tempList= mutableListOf<Predicate>()
                            for (id in ranges){
                                tempList.add(criteriaBuilder.equal(from.get<Int>(path), id.toInt()))
                            }
                            return criteriaBuilder.or(*tempList.toTypedArray())
                        }
                        else->{
                            criteriaBuilder.equal(from.get<Int>(path), searchValue as Int)
                        }
                    }
                }
                String::class.java->{
                    val value=searchValue as String
                    return when(type){
                        null->{
                            criteriaBuilder.equal(from.get<String>(path), value)
                        }
                        "like"->{
                            criteriaBuilder.like(from.get(path), "%${value}%")
                        }
                        "equal"->{
                            criteriaBuilder.equal(from.get<String>(path), value)
                        }
                        ">"->{
                            criteriaBuilder.greaterThan(from.get(path), value)
                        }
                        "<"->{
                            criteriaBuilder.lessThan(from.get<String>(path), value)
                        }
                        ">="->{
                            criteriaBuilder.greaterThanOrEqualTo(from.get<String>(path), value)
                        }
                        "<="->{
                            criteriaBuilder.lessThanOrEqualTo(from.get<String>(path), value)
                        }
                        else->{
                            criteriaBuilder.equal(from.get<String>(path), value)
                        }
                    }
                }
                java.lang.Boolean::class.java->{
                    val value=searchValue as Boolean
                    return when(type){
                        null->{
                            criteriaBuilder.equal(from.get<Boolean>(path), value)
                        }
                        "equal"->{
                            criteriaBuilder.equal(from.get<Boolean>(path), value)
                        }
                        else->{
                            criteriaBuilder.equal(from.get<Boolean>(path), value)
                        }
                    }
                }
                Int::class.java->{
                   val value=searchValue as Int
                   return when(type){
                       null->{
                           criteriaBuilder.equal(from.get<Int>(path), value)
                       }
                       "equal"->{
                           criteriaBuilder.equal(from.get<Int>(path), value)
                       }
                       ">"->{
                           criteriaBuilder.greaterThan(from.get<Int>(path), value)
                       }
                       "<"->{
                           criteriaBuilder.lessThan(from.get<Int>(path), value)
                       }
                       ">="->{
                           criteriaBuilder.greaterThanOrEqualTo(from.get<Int>(path), value)
                       }
                       "<="->{
                           criteriaBuilder.lessThanOrEqualTo(from.get<Int>(path), value)
                       }
                       else->{
                           criteriaBuilder.equal(from.get<Int>(path), value)
                       }
                   }
                }
                java.lang.Long::class.java->{
                    val value=(searchValue.toString()).toLong()
                    return when(type){
                        null->{
                            criteriaBuilder.equal(from.get<Long>(path), value)
                        }
                        "equal"->{
                            criteriaBuilder.equal(from.get<Long>(path), value)
                        }
                        ">"->{
                            criteriaBuilder.greaterThan(from.get<Long>(path), value)
                        }
                        "<"->{
                            criteriaBuilder.lessThan(from.get<Long>(path), value)
                        }
                        ">="->{
                            criteriaBuilder.greaterThanOrEqualTo(from.get<Long>(path), value)
                        }
                        "<="->{
                            criteriaBuilder.lessThanOrEqualTo(from.get<Long>(path), value)
                        }
                        "ids"->{
                            val value=searchValue as String
                            val ranges=value.split(",")
                            val tempList= mutableListOf<Predicate>()
                            for (id in ranges){
                                tempList.add(criteriaBuilder.equal(from.get<Long>(path),id.toInt()))
                            }
                            return criteriaBuilder.or(*tempList.toTypedArray())
                        }
                        else->{
                            criteriaBuilder.equal(from.get<Long>(path), value)
                        }
                    }
                }
                Double::class.java->{
                    val value=searchValue as Double
                    return when(type){
                        null->{
                            criteriaBuilder.equal(from.get<Double>(path), value)
                        }
                        "equal"->{
                            criteriaBuilder.equal(from.get<Double>(path), value)
                        }
                        ">"->{
                            criteriaBuilder.greaterThan(from.get<Double>(path), value)
                        }
                        "<"->{
                            criteriaBuilder.lessThan(from.get<Double>(path), value)
                        }
                        ">="->{
                            criteriaBuilder.greaterThanOrEqualTo(from.get<Double>(path), value)
                        }
                        "<="->{
                            criteriaBuilder.lessThanOrEqualTo(from.get<Double>(path), value)
                        }
                        else->{
                            criteriaBuilder.equal(from.get<Double>(path), value)
                        }
                    }
                }
                LocalDate::class.java->{
                    val value=searchValue as LocalDate
                    return when(type){
                        null->{
                            criteriaBuilder.equal(from.get<LocalDate>(path), value)
                        }
                        "equal"->{
                            criteriaBuilder.equal(from.get<LocalDate>(path), value)
                        }
                        ">"->{
                            criteriaBuilder.greaterThan(from.get<LocalDate>(path), value)
                        }
                        "<"->{
                            criteriaBuilder.lessThan(from.get<LocalDate>(path), value)
                        }
                        ">="->{
                            criteriaBuilder.greaterThanOrEqualTo(from.get<LocalDate>(path), value)
                        }
                        "<="->{
                            criteriaBuilder.lessThanOrEqualTo(from.get<LocalDate>(path), value)
                        }
                        "range"->{
                            val value=searchValue as String
                            val ranges=value.split(",")
                            criteriaBuilder.between(from.get(path), LocalDate.parse(ranges[0]), LocalDate.parse(ranges[1]) )
                        }
                        else->{
                            criteriaBuilder.equal(from.get<LocalDate>(path), value)
                        }
                    }
                }
                LocalDateTime::class.java->{
                    val value=searchValue as LocalDateTime
                    return when(type){
                        null->{
                            criteriaBuilder.equal(from.get<LocalDateTime>(path), value)
                        }
                        "equal"->{
                            criteriaBuilder.equal(from.get<LocalDateTime>(path), value)
                        }
                        ">"->{
                            criteriaBuilder.greaterThan(from.get<LocalDateTime>(path), value)
                        }
                        "<"->{
                            criteriaBuilder.lessThan(from.get<LocalDateTime>(path), value)
                        }
                        ">="->{
                            criteriaBuilder.greaterThanOrEqualTo(from.get<LocalDateTime>(path), value)
                        }
                        "<="->{
                            criteriaBuilder.lessThanOrEqualTo(from.get<LocalDateTime>(path), value)
                        }
                        "range"->{
                            val value=searchValue as String
                            val ranges=value.split(",")
                            criteriaBuilder.between(from.get(path), LocalDateTime.parse(ranges[0]), LocalDateTime.parse(ranges[1]) )
                        }
                        else->{
                            criteriaBuilder.equal(from.get<LocalDateTime>(path), value)
                        }
                    }
                }
            }

            if (field.type.newInstance() is BaseModel){
                return testfilter.subClassFilter(path, criteriaBuilder, from, searchValue, field)
            }
            throw Exception("I don't care with dataType")

        }


        fun <T> moreFilter(criteriaBuilder: CriteriaBuilder, from:Root<T>,more:Map<String, TypeValue>?,modelClass:Class<T>):FilterResult{
            val predicates= mutableListOf<Predicate>()
            val orders= mutableListOf<Order>()
            more?.run{
                this.keys.forEach{
                    val key=it
                    /*TODO Failed to eradicate errors*/
//                    val field=classFieldUtil.getField(modelClass,key)
//                    if (field===null){
//                        throw Exception("Such a field is not defined")
//                    }
                    more[it]?.run {
                        val type=this.type
                        this.value?.run {
//                            null 이 아니거나 빈문자렬인 경우는 아니다.
                            if (this!=""){
                                val path=from.get<String>(key)
                                /*TODO Failed to eradicate errors*/
//                                predicates.add(makePredicate<T>(key, type, criteriaBuilder, from, field))
                            }
                        }
                        this.sort?.run{
                            if (this=="+")
                                orders.add(criteriaBuilder.asc(from.get<Any>(key)))
                            if (this=="-")
                                orders.add(criteriaBuilder.desc(from.get<Any>(key)))
                        }
                    }
                }
            }
            orders. add(criteriaBuilder.desc(from.get<LocalDateTime>("createdAt")))
            return FilterResult(criteriaBuilder.and(*predicates.toTypedArray()), orders)
        }


    }
}