package com.github.kimamik.greenbox.data.courses.dataSource

import android.util.Log
import com.github.kimamik.greenbox.data.courses.model.RemoteCourseDto
import com.github.kimamik.greenbox.data.courses.model.RemoteCoursesContainerDto
import com.github.kimamik.greenbox.domain.common.GBResult
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import javax.inject.Inject

private const val TAG = "KtorRemoteCourseDataSource"

class KtorRemoteCourseDataSource
@Inject constructor(private val client: HttpClient) : RemoteCourseDataSource {
    override suspend fun availableCourses(): GBResult<List<RemoteCourseDto>, Throwable> {
        var body = ""
        return try {
            val response = client.get(RemoteCourseDataSource.AVAILABLE_COURSES_BASE_URL)
            body = response.bodyAsText()
            GBResult.Success(response.body<RemoteCoursesContainerDto>().courses)
        } catch (e: NoTransformationFoundException) {
            val container: RemoteCoursesContainerDto = Json.decodeFromString(body)
            GBResult.Success(container.courses)
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            GBResult.Error(e)
        }
    }
}
