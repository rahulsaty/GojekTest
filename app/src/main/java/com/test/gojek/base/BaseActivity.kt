package com.test.gojek.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {


    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()

    }

    private fun initDataBinding() {
        setContentView(getLayoutId())
    }

    internal fun registerBaseEvents(viewModel: BaseViewModel) {
        viewModel.showToast.observe(this, Observer { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() })
    }



    fun attachFragment(
        fragId: Int,
        fragment: BaseFragment<*>,
        config: FragmentConfig = DEFAULT_FRAGMENT_CONFIG
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (config.shouldAdd)
                add(fragId, fragment, fragment.fragmentTag())
            else
                replace(fragId, fragment, fragment.fragmentTag())

            if (config.addToBackStack) addToBackStack(fragment.fragmentTag())
            commit()
        }
    }


}

val DEFAULT_FRAGMENT_CONFIG = FragmentConfig();
data class FragmentConfig(val shouldAdd: Boolean = false, val addToBackStack: Boolean = true)

