package com.test.gojek.di.component


import com.test.gojek.GojekApplication
import com.test.gojek.deckdesigner.di.module.ViewModelModule
import com.test.gojek.di.builder.ActivityBuilder
import com.test.gojek.di.module.AppModule
import com.test.gojek.di.module.NetworkModule
import com.test.gojek.di.module.OkHttpModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuilder::class,
                AppModule::class,
                NetworkModule::class,
                OkHttpModule::class,
                ViewModelModule::class]
)
abstract class AppComponent : AndroidInjector<GojekApplication> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: GojekApplication): Builder

        fun build(): AppComponent
    }


}