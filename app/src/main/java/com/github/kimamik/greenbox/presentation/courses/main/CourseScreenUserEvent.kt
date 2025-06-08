package com.github.kimamik.greenbox.presentation.courses.main

sealed interface CourseScreenUserEvent {
    data class CheckBookmark(val id: Long) : CourseScreenUserEvent
}