package com.test.gojek.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.test.gojek.di.module.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<V: BaseViewModel> : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory;
    lateinit var viewModel: V
    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun instantiateViewModel(): V


    abstract fun fragmentTag(): String


    private lateinit var viewDataBinding: ViewDataBinding


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = instantiateViewModel()
        setHasOptionsMenu(true)
        retainInstance = true
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is BaseActivity) throw IllegalStateException("BaseFragment can only be attached to BaseActivity")
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        registerBaseEvents()
        if (getBindingVariable() == noViewBinding()) {
            return inflater.inflate(getLayoutId(), container, false)
        }
        initDataBinding(container)
        return viewDataBinding.root
    }


    private fun initDataBinding(container: ViewGroup?) {
        viewDataBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
    }

    private fun registerBaseEvents() {
        getBaseActivity().registerBaseEvents(viewModel);
    }

    fun getBaseActivity(): BaseActivity = activity as BaseActivity

    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDataBinding> getViewDataBinding():T = viewDataBinding as T

    inline fun <reified T : ViewModel> getViewModel(fragment: Fragment, isCommon: Boolean = false): T =
        if (isCommon) {
            ViewModelProviders.of(getBaseActivity(), factory)[T::class.java]
        } else {
            ViewModelProviders.of(fragment, factory)[T::class.java]
        }


}