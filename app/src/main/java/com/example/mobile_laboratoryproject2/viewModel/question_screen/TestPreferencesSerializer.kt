package com.example.mobile_laboratoryproject2.viewModel.question_screen

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.mobile_laboratoryproject2.TestPreferences
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

object TestPreferencesSerializer : Serializer<TestPreferences> {
    override val defaultValue: TestPreferences = TestPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): TestPreferences {
        try {
            return TestPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: TestPreferences, output: OutputStream) {
        withContext(Dispatchers.IO) {
            t.writeTo(output)
        }
    }

}
