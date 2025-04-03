package com.github.kimamik.greenbox.presentation.courses.main

import com.github.kimamik.greenbox.presentation.courses.components.DisplayCourse

sealed interface CourseScreenState {
    data class Loaded(val courses: List<DisplayCourse>) : CourseScreenState
    data object Loading : CourseScreenState
    data object Error : CourseScreenState
}
