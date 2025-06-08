package com.github.kimamik.greenbox.data.di

import com.github.kimamik.greenbox.data.local.DatastoreLocalProperties
import com.github.kimamik.greenbox.domain.local.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
@Suppress("UNUSED")
interface LocalModule {
    @Binds
    fun bindLocalProperties(datastoreLocalProperties: DatastoreLocalProperties): LocalDataSource
}