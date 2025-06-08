package com.github.kimamik.greenbox.presentation.courses.favorite

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kimamik.greenbox.domain.common.GBResult
import com.github.kimamik.greenbox.domain.courses.common.model.Course
import com.github.kimamik.greenbox.domain.courses.usecase.CheckCourseBookmarkUseCase
import com.github.kimamik.greenbox.domain.courses.usecase.SubscribeToFavoriteCoursesUseCase
import com.github.kimamik.greenbox.presentation.courses.components.toDisplayCourse
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Stable
@HiltViewModel
class FavoriteCoursesScreenViewModel @Inject constructor(
    private val subscribeToFavoriteCourses: SubscribeToFavoriteCoursesUseCase,
    private val checkCourseBookmark: CheckCourseBookmarkUseCase
) : ViewModel() {
    private val _state =
        MutableStateFlow<FavoriteCoursesScreenState>(FavoriteCoursesScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            subscribeToFavoriteCourses().collect { result ->
                when (result) {
                    is GBResult.Error<*, *> -> _state.update {
                        FavoriteCoursesScreenState.Error
                    }

                    is GBResult.Success<List<Course>, *> -> _state.update {
                        FavoriteCoursesScreenState.Loaded(result.data.map { it.toDisplayCourse() })
                    }
                }
            }
        }
    }

    fun onEvent(event: FavoriteCoursesScreenUserEvent) {
        when (event) {
            is FavoriteCoursesScreenUserEvent.UnmarkCourse -> onUnmarkCourse(event.id)
        }
    }

    private fun onUnmarkCourse(id: Long) {
        viewModelScope.launch {
            checkCourseBookmark(id)
        }
    }
}