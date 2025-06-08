package com.github.kimamik.greenbox.presentation.courses.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kimamik.greenbox.domain.common.GBResult
import com.github.kimamik.greenbox.domain.courses.common.model.Course
import com.github.kimamik.greenbox.domain.courses.common.repository.RemoteCourseRepository
import com.github.kimamik.greenbox.domain.local.LocalDataSource
import com.github.kimamik.greenbox.presentation.courses.components.toDisplayCourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseScreenViewModel @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteCourseRepository: RemoteCourseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<CourseScreenState>(CourseScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val courses = remoteCourseRepository.availableCourses()
            when (courses) {
                is GBResult.Error<*, *> -> {
                    _state.value = CourseScreenState.Error
                }

                is GBResult.Success<List<Course>, *> -> {
                    _state.value =
                        CourseScreenState.Loaded(courses.data.map { it.toDisplayCourse() })
                }
            }
        }
    }
}