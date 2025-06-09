package com.github.kimamik.greenbox.domain.courses.usecase

import com.github.kimamik.greenbox.domain.local.LocalDataSource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CheckCourseBookmarkUseCase
@Inject constructor(
    private val localDataSource: LocalDataSource
) {
    suspend operator fun invoke(id: Long) {
        val oldCourses = localDataSource.localProperties().first().favoriteCourses
        if (oldCourses.contains(id)) {
            localDataSource.removeCourseFromFavorite(id)
        } else {
            localDataSource.addCourseToFavorite(id)
        }
    }
}