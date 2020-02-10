package com.test.news.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Default/standard RxJava2 schedulers provider
 */
class DefaultSchedulersProvider @Inject constructor() : SchedulersProvider {
    override fun getUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
    override fun getIoScheduler(): Scheduler = Schedulers.io()
    override fun getComputationScheduler(): Scheduler = Schedulers.computation()
}
