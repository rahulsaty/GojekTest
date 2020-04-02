package com.test.gojek.ui.viemodel

import androidx.lifecycle.MutableLiveData
import com.test.gojek.R
import com.test.gojek.base.BaseViewModel
import com.test.gojek.net.GitHubClient
import com.test.gojek.net.model.Repository
import java.io.IOException
import javax.inject.Inject


class TrendingViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var gitHubClient: GitHubClient;

    var data = MutableLiveData<List<Repository>>()

    var isNetworkError = MutableLiveData<Boolean>()

    var errorMessage = MutableLiveData<String?>()


     fun loadData(isRefreshed:Boolean = false) {

         if(data.value == null || isRefreshed) {
             gitHubClient.getList()
                 .call({ list -> data.value = list }, { t: Throwable -> handleError(t) });
         }

    }

    override fun handleError(e: Throwable) {
        isLoading.set(false)
       if (e is IOException) {
            showToast.value = applicationContext.getString(R.string.no_internet_found)
            isNetworkError.value = true;
        } else {
            showToast.value =  e.message.toString()
            errorMessage.value = e.message.toString()
            isNetworkError.value = true;
        }
    }


}

