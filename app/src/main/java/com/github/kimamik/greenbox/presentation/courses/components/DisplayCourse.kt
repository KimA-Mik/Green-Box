package com.github.kimamik.greenbox.presentation.courses.components

import com.github.kimamik.greenbox.domain.courses.common.model.Course

data class DisplayCourse(
    val id: Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: Long,
    val publishDate: Long,
    val hasLike: Boolean
)

fun Course.toDisplayCourse() = DisplayCourse(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    publishDate = publishDate,
    hasLike = hasLike
)