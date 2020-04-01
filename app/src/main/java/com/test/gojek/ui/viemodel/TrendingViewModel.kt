package com.test.gojek.ui.viemodel

import androidx.lifecycle.MutableLiveData
import com.test.gojek.base.BaseViewModel
import com.test.gojek.net.GitHubClient
import com.test.gojek.net.model.Repository
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class TrendingViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var gitHubClient: GitHubClient;

    var data = MutableLiveData<List<Repository>>()

    var isNetworkError = MutableLiveData<Boolean?>()

    var errorMessage = MutableLiveData<String?>()


     fun loadData(isRefreshed:Boolean = false) {

         if(data.value == null || isRefreshed) {
             gitHubClient.getList()
                 .call({ list -> data.value = list }, { t: Throwable -> handleError(t) });
         }

    }

    override fun handleError(e: Throwable) {
        isLoading.set(false)
        if (e is HttpException) {
            val body: ResponseBody? = e.response()!!.errorBody()
            showToast.value =getErrorMessage(body);
        } else if (e is IOException) {
            showToast.value = "No internet found"
            isNetworkError.value = true;
        } else {
            showToast.value =  e.message.toString()
        }
    }


}

