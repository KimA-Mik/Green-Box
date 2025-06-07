package com.github.kimamik.greenbox.data.courses.dataSource

import com.github.kimamik.greenbox.data.courses.model.RemoteCourseDto
import com.github.kimamik.greenbox.domain.common.GBResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class KtorRemoteCourseDataSource
@Inject constructor(private val client: HttpClient) : RemoteCourseDataSource {
    override suspend fun availableCourses(): GBResult<List<RemoteCourseDto>, Throwable> = try {
        val response = client.get(RemoteCourseDataSource.AVAILABLE_COURSES_BASE_URL)
        GBResult.Success(response.body<List<RemoteCourseDto>>())
    } catch (e: Exception) {
        GBResult.Error(e)
    }
}
