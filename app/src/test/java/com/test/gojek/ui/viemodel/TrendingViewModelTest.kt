package com.test.gojek.ui.viemodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.gojek.mockDummyData
import com.test.gojek.net.GitHubClient
import com.test.gojek.net.model.Repository
import com.test.gojek.rx.AppSchedulerProvider
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RuntimeEnvironment
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


@RunWith(MockitoJUnitRunner::class)
class TrendingViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit  var trendingViewModel: TrendingViewModel
    @Mock
    lateinit var client: GitHubClient
    @Mock
    lateinit var context: Context;
    @InjectMocks
    lateinit var appSchedulerProvider: AppSchedulerProvider

    @Before
    fun setUp() {
        trendingViewModel = TrendingViewModel()
        trendingViewModel.appSchedulerProvider = appSchedulerProvider;
        trendingViewModel.applicationContext = context
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        trendingViewModel.gitHubClient = client
    }

    @Test
    fun testWith_NetWorkError() {
       given(client.getList())
            .willReturn(Single.error(IOException()))

        trendingViewModel.loadData()

        assertEquals(true,trendingViewModel.isNetworkError.value)
    }

    @Test
    fun testWith_HttpError() {
       given(client.getList()).willReturn(
            Single.error(
                HttpException(
                    Response.error<Any>(
                        400, ResponseBody.create(
                            MediaType.parse("application/json"),  "{\"message\":\"HTTP 400 Response.error()\"}"
                        )
                    )
                )
            )
        )
        trendingViewModel.loadData()
        assertEquals("HTTP 400 Response.error()", trendingViewModel.errorMessage.value)
    }
    @Test
    fun getDatatTest_Success() {
        val dummydata: List<Repository> = mockDummyData()
        given(client.getList())
            .willReturn(Single.just<List<Repository>>(dummydata))
        trendingViewModel.loadData()

        trendingViewModel.data.value?.let {
            Assert.assertTrue(it.size.compareTo(0)===1)

            for (i in 0 until  it.size-1) {
                val repository: Repository = it.get(i)
                assertEquals("User $i", repository.name)
                assertEquals("Author $i", repository.author)
                assertEquals("www.google.com/sample.png $i", repository.avatar)
                assertEquals(i*10, (repository.stars).toInt())
                assertEquals(i*10, repository.forks.toInt())
                assertEquals("langauge $i", repository.language)
            }
        }


    }

}