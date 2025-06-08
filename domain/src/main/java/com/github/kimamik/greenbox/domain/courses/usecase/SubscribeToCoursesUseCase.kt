package com.github.kimamik.greenbox.domain.courses.usecase

import com.github.kimamik.greenbox.domain.common.GBResult
import com.github.kimamik.greenbox.domain.common.valueOr
import com.github.kimamik.greenbox.domain.courses.common.model.Course
import com.github.kimamik.greenbox.domain.courses.common.repository.RemoteCourseRepository
import com.github.kimamik.greenbox.domain.local.LocalDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubscribeToCoursesUseCase @Inject constructor(
    private val remoteCourseRepository: RemoteCourseRepository,
    private val localDataSource: LocalDataSource
) {
    operator fun invoke() = flow<GBResult<List<Course>, Throwable>> {
        val courses =
            MutableStateFlow<GBResult<List<Course>, Throwable>>(GBResult.Success(emptyList()))
        val favoriteCourses = localDataSource.localProperties().map { it.favoriteCourses }
        courses.value = remoteCourseRepository.availableCourses()

        val out = combine(courses, favoriteCourses) { all, favorite ->
            val courses = all.valueOr {
                return@combine GBResult.Error(data = it)
            }

            val result = courses.map {
                it.copy(hasLike = favorite.contains(it.id))
            }
            GBResult.Success<List<Course>, Throwable>(result)
        }

        emitAll(out)
    }
}
