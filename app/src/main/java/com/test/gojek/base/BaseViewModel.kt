package com.test.gojek.base

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.gojek.rx.RxSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var applicationContext: Context

    @Inject
    lateinit var appSchedulerProvider: RxSchedulerProvider

    val showToast = MutableLiveData<String>()
    val hideKeyBoard = MutableLiveData<Boolean>()
    val isLoading = ObservableBoolean(false)
    private var compositeDisposable = CompositeDisposable();


    //Ext. func for api call
    fun <T> Single<T>.call(success: (T) -> Unit, failure: (t: Throwable) -> Unit = ::handleError) =
        subscribeOn(appSchedulerProvider.io()).observeOn(appSchedulerProvider.ui()).subscribe(
            success,
            failure
        ).apply {
            isLoading.set(true)
            compositeDisposable.add(this)
        }


    open fun handleError(e: Throwable) {
        isLoading.set(false)
        if (e is HttpException) {
            val body: ResponseBody? = e.response()!!.errorBody()
            showToast.value =getErrorMessage(body);
        } else if (e is IOException) {
            showToast.value = "No internet found"
        } else {
            showToast.value =  e.message.toString()
        }
    }

     fun getErrorMessage(responseBody: ResponseBody?): String {
        try {
            val jsonObject = JSONObject(responseBody!!.string())
            return jsonObject.getString("message")
        } catch (e: Exception) {
            return e.message.toString()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        compositeDisposable.clear()
        compositeDisposable = CompositeDisposable()
    }
}
