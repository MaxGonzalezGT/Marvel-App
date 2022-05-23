package com.android.data.repository.network.util

import com.android.data.util.Constants.Companion.BASE_URL
import java.net.HttpURLConnection

object MockNetworkConfig {
    val baseUrl = BASE_URL
    val randomBaseUrl = "https://unabletoresolvehost.com/"
    val status = HttpURLConnection.HTTP_CLIENT_TIMEOUT
}