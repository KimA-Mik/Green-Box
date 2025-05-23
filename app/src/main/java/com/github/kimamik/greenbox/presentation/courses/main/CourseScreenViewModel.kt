package com.github.kimamik.greenbox.presentation.courses.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CourseScreenViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<CourseScreenState>(CourseScreenState.Loading)
    val state = _state.asStateFlow()


}