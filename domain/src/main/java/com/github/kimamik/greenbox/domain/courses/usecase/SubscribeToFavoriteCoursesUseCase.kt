package com.github.kimamik.greenbox.domain.courses.usecase

import com.github.kimamik.greenbox.domain.common.GBResult
import com.github.kimamik.greenbox.domain.courses.common.model.Course
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubscribeToFavoriteCoursesUseCase @Inject constructor(
    private val subscribeToCourses: SubscribeToCoursesUseCase,
) {
    operator fun invoke() = subscribeToCourses().map { result ->
        when (result) {
            is GBResult.Error<*, Throwable> -> GBResult.Error<List<Course>, Throwable>(result.data)
            is GBResult.Success<List<Course>, *> -> GBResult.Success(result.data.filter { it.hasLike })
        }
    }
}