package com.github.kimamik.greenbox.domain.courses.common.model

data class Course(
    val id: Long,
    val title: String,
    val text: String,
    //TODO: maybe make price and rate as actual numbers
    val price: String,
    val rate: String,
    val startDate: Long,
    val publishDate: Long,
    val hasLike: Boolean

)
