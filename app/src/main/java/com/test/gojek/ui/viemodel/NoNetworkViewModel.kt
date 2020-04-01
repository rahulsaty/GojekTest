package com.test.gojek.ui.viemodel

import androidx.lifecycle.MutableLiveData
import com.test.gojek.base.BaseViewModel
import javax.inject.Inject


class NoNetworkViewModel  @Inject constructor(): BaseViewModel() {

    var retry = MutableLiveData<Boolean>()

    fun retry(){
       retry.value = true;
   }

}

