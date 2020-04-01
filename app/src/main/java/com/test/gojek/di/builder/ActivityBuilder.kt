package com.test.gojek.di.builder

import com.test.gojek.ui.GitTrendingActivity
import com.test.gojek.ui.TrendingProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [TrendingProvider::class])
    internal abstract fun bindTrendingActivity(): GitTrendingActivity

}

