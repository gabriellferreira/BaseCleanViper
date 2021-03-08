package com.gabriellferreira.basecleanviper.domain.util.entity

class Page<T>(
    val previousPage: Int?,
    val nextPage: Int?,
    val itemList: List<T>
)