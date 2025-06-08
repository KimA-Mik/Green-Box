package com.github.kimamik.greenbox.presentation.courses.favorite

sealed interface FavoriteCoursesScreenUserEvent {
    data class UnmarkCourse(val id: Long) : FavoriteCoursesScreenUserEvent
}