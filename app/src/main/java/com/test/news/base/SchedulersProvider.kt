package com.test.news.base

import io.reactivex.Scheduler

/**
 * Rx Java schedulers abstraction for RxJava2.
 */
interface SchedulersProvider {
    fun getUiScheduler(): Scheduler
    fun getIoScheduler(): Scheduler
    fun getComputationScheduler(): Scheduler
}
