package com.github.kimamik.greenbox.presentation.courses.favorite

import com.github.kimamik.greenbox.presentation.courses.components.DisplayCourse

sealed interface FavoriteCoursesScreenState {
    data class Loaded(val courses: List<DisplayCourse>) : FavoriteCoursesScreenState
    data object Loading : FavoriteCoursesScreenState
    data object Error : FavoriteCoursesScreenState
}