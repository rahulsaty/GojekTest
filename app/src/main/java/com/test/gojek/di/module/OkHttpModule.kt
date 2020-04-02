package com.test.gojek.di.module

import android.content.Context
import android.util.Log
import com.test.gojek.BuildConfig
import com.test.gojek.R
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton


@Module
class OkHttpModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        clientBuilder: OkHttpClient.Builder,context: Context
    ): OkHttpClient {

        addLoggingInterceptor(clientBuilder)
        val httpCacheDirectory = File(context.cacheDir, "offlineCache")
        val cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)

        return clientBuilder.cache(cache).addInterceptor(CacheInterceptor()).addInterceptor(OfflineCacheInterceptor())
            .build();
        return clientBuilder.build()
    }


    @Provides
    internal fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    internal fun provideBaseUrl(context: Context): HttpUrl {
        return HttpUrl.parse(context.getString(R.string.base_url))!!
    }


    internal fun addLoggingInterceptor(clientBuilder: OkHttpClient.Builder) {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
            clientBuilder.addNetworkInterceptor(loggingInterceptor)
        }
    }


}
