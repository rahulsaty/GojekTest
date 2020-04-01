package com.test.gojek.net

import com.test.gojek.net.model.Repository
import io.reactivex.Single
import retrofit2.http.GET


interface RestApi {

     @GET("repositories")
     fun  repositoryList() : Single<List<Repository>>
}