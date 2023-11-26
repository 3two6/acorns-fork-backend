package com.egc.baseapi.jpa.base;


import com.egc.baseapi.util.MyObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Predicate;

public class testfilter {
    public static <T> Predicate filter(String path, CriteriaBuilder criteriaBuilder, Root from, T filter) {
        if (filter != null && DefaultGroovyMethods.getAt(filter, path) != null)
            return ((Predicate) (criteriaBuilder.equal(from.get(path), DefaultGroovyMethods.getAt(filter, path))));
        return null;
    }

    public static Predicate subClassFilter(String path, CriteriaBuilder criteriaBuilder, Root from, Object src, Field field) {
        return ((Predicate) (criteriaBuilder.equal(from.get(path), MyObjectMapper.convertfromMap(DefaultGroovyMethods.asType(src, Map.class), (Class<?>) field.getType()))));
    }

}
