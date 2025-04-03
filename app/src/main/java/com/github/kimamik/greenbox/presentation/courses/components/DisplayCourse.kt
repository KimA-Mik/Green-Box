package com.github.kimamik.greenbox.presentation.courses.components

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
