package com.test.gojek.deckdesigner.di.module


import androidx.lifecycle.ViewModelProvider
import com.test.gojek.di.module.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {




    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}