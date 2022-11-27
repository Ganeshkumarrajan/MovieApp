package com.anonymous.movieapp.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

fun MockWebServer.invokeResult(fileName: String, code: Int) {
    val stream = javaClass.classLoader?.getResourceAsStream(fileName)
    val source = stream?.let { stream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}