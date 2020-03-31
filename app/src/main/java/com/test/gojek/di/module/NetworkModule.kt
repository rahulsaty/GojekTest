package com.test.gojek.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.gotjek.net.RestApi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {


    internal fun createInstance(
        client: OkHttpClient, rxJavaCallAdapterFactory: RxJava2CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory,
        baseUrl: HttpUrl
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient,
                                 rxJavaCallAdapterFactory: RxJava2CallAdapterFactory,
                                 gsonConverterFactory: GsonConverterFactory,
                                 baseUrl: HttpUrl): Retrofit {
        return createInstance(client, rxJavaCallAdapterFactory, gsonConverterFactory, baseUrl)
    }

    @Provides
    @Singleton
    internal fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideGson(builder: GsonBuilder): Gson {
        return builder.create()
    }

    @Singleton
    @Provides
     internal fun provideCommonGsonBuilder():GsonBuilder {
        var gsonBuilder =  GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder;
    }

    @Singleton
    @Provides
    internal  fun provideTrexRestApi(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }
}