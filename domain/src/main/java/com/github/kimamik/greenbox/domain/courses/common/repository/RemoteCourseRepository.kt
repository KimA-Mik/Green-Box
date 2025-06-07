package com.github.kimamik.greenbox.domain.courses.common.repository

import com.github.kimamik.greenbox.domain.common.GBResult
import com.github.kimamik.greenbox.domain.courses.common.model.Course

interface RemoteCourseRepository {
    suspend fun availableCourses(): GBResult<List<Course>, Throwable>
}