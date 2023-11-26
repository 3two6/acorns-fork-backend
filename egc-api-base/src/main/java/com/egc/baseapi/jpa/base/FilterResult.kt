package com.egc.baseapi.jpa.base

import jakarta.persistence.criteria.Order
import jakarta.persistence.criteria.Predicate


class FilterResult(val predicate: Predicate?, val orders:MutableList<Order>)