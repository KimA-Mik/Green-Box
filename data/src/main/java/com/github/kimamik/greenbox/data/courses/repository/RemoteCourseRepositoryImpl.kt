package com.github.kimamik.greenbox.data.courses.repository

import com.github.kimamik.greenbox.data.courses.dataSource.RemoteCourseDataSource
import com.github.kimamik.greenbox.data.courses.mappers.toCourse
import com.github.kimamik.greenbox.data.courses.model.RemoteCourseDto
import com.github.kimamik.greenbox.domain.common.GBResult
import com.github.kimamik.greenbox.domain.courses.common.model.Course
import com.github.kimamik.greenbox.domain.courses.common.repository.RemoteCourseRepository
import jakarta.inject.Inject
import java.text.SimpleDateFormat
import java.util.Locale

class RemoteCourseRepositoryImpl @Inject constructor(
    private val dataSource: RemoteCourseDataSource
) : RemoteCourseRepository {
    private val sdf = SimpleDateFormat(RemoteCourseDataSource.DATE_FORMAT, Locale.getDefault())
    override suspend fun availableCourses(): GBResult<List<Course>, Throwable> =
        when (val result = dataSource.availableCourses()) {
            is GBResult.Error<*, Throwable> -> GBResult.Error(result.data)
            is GBResult.Success<List<RemoteCourseDto>, *> -> GBResult.Success(result.data.map {
                it.toCourse(
                    sdf
                )
            })
        }
}