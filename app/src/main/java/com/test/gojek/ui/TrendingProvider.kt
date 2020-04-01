package com.test.gojek.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TrendingProvider {

    @ContributesAndroidInjector
    abstract fun provideTrendingListFragment(): TrendingListFragment



}