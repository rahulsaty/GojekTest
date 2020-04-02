package com.test.gojek.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.test.gojek.BR
import com.test.gojek.R
import com.test.gojek.base.BaseAdapter
import com.test.gojek.databinding.TrendingItemBinding
import com.test.gojek.net.model.Repository
import kotlinx.android.synthetic.main.trending_item.view.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingListAdapter @Inject constructor() : BaseAdapter<TrendingItemBinding>() {

    @Inject
    lateinit var context: Context;

    override fun getBindingVariable(): Int = BR.data
    override fun getLayoutId(): Int = R.layout.trending_item

    override fun context(): Context = context;

    override fun getListData(): List<Repository> = data;

    var selectedPos: Int = -1;

    var data:List<Repository> = ArrayList<Repository>();

    fun setData(data: ArrayList<Repository>) {
        this.data = data
        notifyDataSetChanged()
    }



    private fun setTextViewDrawableColor(textView: TextView, color: String) {
        for (drawable in textView!!.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter =
                    PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data.size
    }

    override fun onBindViewHolder(holder: BaseAdapter<*>.ViewHolder, position: Int) {
        var repository = data.get(position)
        holder.bind(repository)

        holder.itemView.setOnClickListener { v: View? ->
            if(selectedPos != -1) {
                var prevValue = data.get(selectedPos)
                prevValue.expanded = false;
                notifyItemChanged(selectedPos)
            }

            selectedPos = position;
            repository.expanded = !repository.expanded
            notifyItemChanged(position)

        }

        if (!TextUtils.isEmpty(repository?.language)) {
            holder.itemView.lang.visibility = View.VISIBLE
            holder.itemView.lang.text = repository.language
            repository.languageColor?.let { setTextViewDrawableColor(holder.itemView.lang, it) }
        } else {
            holder.itemView.lang.visibility = View.GONE
        }

    }



}