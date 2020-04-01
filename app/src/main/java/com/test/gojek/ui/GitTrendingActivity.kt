package com.test.gojek.ui

import android.os.Bundle
import android.view.Menu
import com.test.gojek.R
import com.test.gojek.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class GitTrendingActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.title = "Trending"
        setSupportActionBar(toolbar)
        toolbar_title.text = toolbar.title
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        if(savedInstanceState == null)
        attachFragment(R.id.container, TrendingListFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}
