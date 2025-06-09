package com.github.kimamik.greenbox.data.local

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class LocalSchema(
    val showOnboarding: Boolean = true,
    val favoriteCourses: Set<Long> = setOf()
)

@OptIn(ExperimentalSerializationApi::class)
object LocalSchemaSerializer : Serializer<LocalSchema> {
    override val defaultValue
        get() = LocalSchema()

    override suspend fun readFrom(input: InputStream): LocalSchema = try {
        withContext(Dispatchers.IO) {
            return@withContext ProtoBuf.decodeFromByteArray<LocalSchema>(input.readBytes())
        }
    } catch (exception: SerializationException) {
        throw CorruptionException("Cannot read proto.", exception)
    }

    override suspend fun writeTo(t: LocalSchema, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(ProtoBuf.encodeToByteArray(t))
        }
    }
}

val Context.localSchemaDataStore: DataStore<LocalSchema> by dataStore(
    fileName = "data.pb",
    serializer = LocalSchemaSerializer
)