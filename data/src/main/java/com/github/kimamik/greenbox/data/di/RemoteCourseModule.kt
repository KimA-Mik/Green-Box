package com.github.kimamik.greenbox.data.di

import com.github.kimamik.greenbox.data.courses.dataSource.KtorRemoteCourseDataSource
import com.github.kimamik.greenbox.data.courses.dataSource.RemoteCourseDataSource
import com.github.kimamik.greenbox.data.courses.repository.RemoteCourseRepositoryImpl
import com.github.kimamik.greenbox.domain.courses.common.repository.RemoteCourseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteCourseModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }
    }

    @Provides
    @Singleton
    fun provideKtorRemoteCourseDataSource(client: HttpClient) = KtorRemoteCourseDataSource(client)

    @Provides
    @Singleton
    fun provideRemoteCourseRepositoryImpl(dataSource: RemoteCourseDataSource) =
        RemoteCourseRepositoryImpl(dataSource)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class CourseDataSourceBindingModule {
    @Binds
    @Singleton
    abstract fun bindKtorDataSource(ktorRemoteCourseDataSource: KtorRemoteCourseDataSource): RemoteCourseDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteCourseRepository(remoteCourseRepository: RemoteCourseRepositoryImpl): RemoteCourseRepository
}
