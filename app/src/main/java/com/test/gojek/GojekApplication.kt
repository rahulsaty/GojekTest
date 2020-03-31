package com.test.gojek

import com.test.gojek.di.component.AppComponent
import com.test.gojek.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

class GojekApplication: DaggerApplication() {


    override fun applicationInjector(): AppComponent {
        return DaggerAppComponent.builder().application(this).build()
    }
}