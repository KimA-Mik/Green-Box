package com.github.kimamik.greenbox.data.courses.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCourseDto(
    val id: Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val publishDate: String,
    val hasLike: Boolean
)
