package com.github.kimamik.greenbox.data.local

import android.content.Context
import com.github.kimamik.greenbox.domain.local.LocalDataSource
import com.github.kimamik.greenbox.domain.local.LocalProperties
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreLocalProperties @Inject constructor(
    @ApplicationContext context: Context
) : LocalDataSource {
    private val dataStore = context.localSchemaDataStore

    override fun localProperties(): Flow<LocalProperties> =
        dataStore.data.map { it.toLocalProperties() }

    override suspend fun updateShowOnboarding(value: Boolean) {
        dataStore.updateData { it.copy(showOnboarding = value) }
    }
}