package com.test.gojek.di.module

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit


class CacheInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val originalResponse = chain.proceed(request)
        val cacheControl = originalResponse.header("Cache-Control")

        return if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-stale=0")) {
            val cc = CacheControl.Builder()
                    .maxStale(2, TimeUnit.HOURS)
                    .build()
            request = request.newBuilder()
                    .cacheControl(cc)
                    .build()
            chain.proceed(request)
        } else {
            originalResponse
        }
    }
}