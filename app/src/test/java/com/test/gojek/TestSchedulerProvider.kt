package com.test.gojek

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private val mTestScheduler: TestScheduler) {
    fun computation(): Scheduler {
        return mTestScheduler
    }

    fun io(): Scheduler {
        return mTestScheduler
    }

    fun ui(): Scheduler {
        return mTestScheduler
    }

}