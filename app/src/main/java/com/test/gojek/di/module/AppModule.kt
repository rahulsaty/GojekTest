package com.test.gojek.di.module

import android.content.Context
import com.test.gojek.GojekApplication
import com.test.gojek.rx.AppSchedulerProvider
import com.test.gojek.rx.RxSchedulerProvider

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: GojekApplication): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): RxSchedulerProvider {
        return AppSchedulerProvider()
    }
}