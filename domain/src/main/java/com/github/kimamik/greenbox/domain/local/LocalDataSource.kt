package com.github.kimamik.greenbox.domain.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun localProperties(): Flow<LocalProperties>
    suspend fun updateShowOnboarding(value: Boolean)
    suspend fun addCourseToFavorite(id: Long)
    suspend fun removeCourseFromFavorite(id: Long)
}