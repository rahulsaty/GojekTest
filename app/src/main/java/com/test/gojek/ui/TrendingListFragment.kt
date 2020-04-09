package com.test.gojek.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.test.gojek.BR
import com.test.gojek.R
import com.test.gojek.base.BaseFragment
import com.test.gojek.net.model.Repository
import com.test.gojek.ui.adapter.TrendingListAdapter
import com.test.gojek.ui.viemodel.TrendingViewModel
import kotlinx.android.synthetic.main.fragmet_list.*
import javax.inject.Inject

class TrendingListFragment : BaseFragment<TrendingViewModel>() {

    @Inject
    lateinit var adapter: TrendingListAdapter

    override fun getLayoutId(): Int = R.layout.fragmet_list

    override fun instantiateViewModel(): TrendingViewModel = getViewModel(this)


    override fun fragmentTag(): String = TrendingListFragment::class.java.simpleName


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout.setColorSchemeResources(R.color.colorFontGreyDark)

        var skeletonScreen: SkeletonScreen? = null;

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        if (savedInstanceState != null) {
            recyclerView.adapter = this.adapter
        } else {
            skeletonScreen = Skeleton.bind(recyclerView)
                .adapter(adapter)
                .shimmer(true)
                .angle(20)
                .frozen(false)
                .duration(1200)
                .count(10)
                .load(R.layout.trending_skelton_item)
                .show()
        }
        viewModel.getUsers().observe(this, Observer { repositories: List<Repository> ->
            skeletonScreen?.hide()
            adapter.setData(repositories as ArrayList<Repository>)
            swipeRefreshLayout.isRefreshing = false
        })
        viewModel.isNetworkError.observe(this, Observer { aBoolean: Boolean? ->
            aBoolean?.let {
                skeletonScreen?.hide()
                getBaseActivity().attachFragment(R.id.container, NoNetworkFragment())
            }


        })
        viewModel.errorMessage.observe(
            this,
            Observer { message: String? ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            })

        swipeRefreshLayout.setOnRefreshListener { viewModel.pulltoRefresh() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("hasdata", viewModel.getUsers().value?.size != 0)
    }

    override fun getBindingVariable(): Int = BR.viewModel
}