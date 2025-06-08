package com.github.kimamik.greenbox.presentation.courses.main

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kimamik.greenbox.domain.common.GBResult
import com.github.kimamik.greenbox.domain.courses.common.model.Course
import com.github.kimamik.greenbox.domain.courses.usecase.CheckCourseBookmarkUseCase
import com.github.kimamik.greenbox.domain.courses.usecase.SubscribeToCoursesUseCase
import com.github.kimamik.greenbox.presentation.courses.components.toDisplayCourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
@HiltViewModel
class CourseScreenViewModel @Inject constructor(
    private val subscribeToCourses: SubscribeToCoursesUseCase,
    private val checkCourseBookmark: CheckCourseBookmarkUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<CourseScreenState>(CourseScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            subscribeToCourses().collect { result ->
                when (result) {
                    is GBResult.Error<*, *> -> _state.update {
                        CourseScreenState.Error
                    }

                    is GBResult.Success<List<Course>, *> -> _state.update {
                        CourseScreenState.Loaded(result.data.map { it.toDisplayCourse() })
                    }
                }
            }
        }
    }

    fun onEvent(event: CourseScreenUserEvent) {
        when (event) {
            is CourseScreenUserEvent.CheckBookmark -> onCheckBookmark(event.id)
        }
    }

    private fun onCheckBookmark(id: Long) = viewModelScope.launch {
        checkCourseBookmark(id)
    }
}