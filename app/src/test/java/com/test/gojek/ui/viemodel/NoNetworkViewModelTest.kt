package com.test.gojek.ui.viemodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NoNetworkViewModelTest{

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit  var noNetworkViewModel: NoNetworkViewModel

    @Before
    fun  setUp(){
        noNetworkViewModel = NoNetworkViewModel()
    }

    @Test
    fun test_retry(){
        noNetworkViewModel.retry()
        assertEquals(true,noNetworkViewModel.retry.value)
    }



}