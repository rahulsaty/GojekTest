package com.test.gojek.deckdesigner.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.gojek.di.module.ViewModelFactory
import com.test.gojek.di.module.ViewModelKey
import com.test.gojek.ui.viemodel.NoNetworkViewModel
import com.test.gojek.ui.viemodel.TrendingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(TrendingViewModel::class)
    internal abstract fun bindTrendingViewModel(trendingViewModel: TrendingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoNetworkViewModel::class)
    internal abstract fun bindNoNetworkViewModel(noNetworkViewModel: NoNetworkViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}