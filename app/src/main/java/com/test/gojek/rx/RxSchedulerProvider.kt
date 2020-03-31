package com.test.gojek.rx

import io.reactivex.Scheduler


interface RxSchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
