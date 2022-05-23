package com.android.data.repository.network.util

import okhttp3.mockwebserver.MockWebServer

class MockServer  {
    companion object {
        val server = MockWebServer()
    }
}