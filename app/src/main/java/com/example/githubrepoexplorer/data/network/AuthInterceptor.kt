package com.example.githubrepoexplorer.data.network

import com.example.githubrepoexplorer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (request.url.toString().contains(BuildConfig.BASE_URL)) {
            val newRequest = chain.request()
                .newBuilder()
                .addHeader(AUTH_HEADER, "Bearer ${BuildConfig.API_KEY}")
                .build()
             chain.proceed(newRequest)
        } else {
            chain.proceed(request)
        }
    }
}