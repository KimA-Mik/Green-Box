package com.github.kimamik.greenbox.data.courses.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCoursesContainerDto(
    val courses: List<RemoteCourseDto>
)
