package com.test.gojek.base

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.test.gojek.BR


abstract class BaseAdapter<V : ViewDataBinding> :
    RecyclerView.Adapter<BaseAdapter<*>.ViewHolder>() {
    private lateinit var viewDataBinding: ViewDataBinding
    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun context(): Context

    abstract fun getListData():List<Any>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: V =
            DataBindingUtil.inflate(
                LayoutInflater.from(context()),
                getLayoutId(),
                parent,
                false
            )
        return ViewHolder(binding)
    }



    inner class ViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Any) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
        }


    }

}
