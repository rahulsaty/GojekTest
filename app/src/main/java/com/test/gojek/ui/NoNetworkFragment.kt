package com.test.gojek.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.test.gojek.BR
import com.test.gojek.R
import com.test.gojek.base.BaseFragment
import com.test.gojek.ui.viemodel.NoNetworkViewModel


class NoNetworkFragment : BaseFragment<NoNetworkViewModel>() {


    override fun getLayoutId(): Int = R.layout.fragment_no_network

    override fun instantiateViewModel(): NoNetworkViewModel = getViewModel(this)


    override fun fragmentTag(): String = NoNetworkFragment::class.java.simpleName

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.retry.observe(this, Observer { getBaseActivity().attachFragment(R.id.container,TrendingListFragment()) })
    }

    override fun getBindingVariable(): Int = BR.viewModel
}

