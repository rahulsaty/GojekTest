package com.test.gojek.di.builder

import com.test.gojek.ui.GitTrendingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindTrendingActivity(): GitTrendingActivity

}

