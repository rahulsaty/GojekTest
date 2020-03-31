package com.test.gotjek.net

import com.test.gotjek.net.model.Repository
import io.reactivex.Single
import retrofit2.http.GET


interface RestApi {

     @GET("repositories")
     fun  repositoryList() : Single<List<Repository>>
}