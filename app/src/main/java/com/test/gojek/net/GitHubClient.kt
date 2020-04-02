package com.test.gojek.net

import com.test.gojek.net.model.Repository
import io.reactivex.Single
import javax.inject.Inject

class GitHubClient @Inject constructor() {

    @Inject
    lateinit var api: RestApi;

    fun getList(): Single<List<Repository>> {
        return api.repositoryList()
    }
}
