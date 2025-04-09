package com.github.kimamik.greenbox.data.courses.dataSource

import com.github.kimamik.greenbox.data.courses.model.RemoteCourseDto
import com.github.kimamik.greenbox.domain.common.GBResult

interface RemoteCourseDataSource {
    suspend fun availableCourses(): GBResult<List<RemoteCourseDto>, Throwable>

    companion object {
        const val AVAILABLE_COURSES_BASE_URL =
            "https://raw.githubusercontent.com/KimA-Mik/Green-Box/refs/heads/master/.github/Mock/courses.json/"

        const val DATE_FORMAT = "yyyy-MM-dd"
    }
}