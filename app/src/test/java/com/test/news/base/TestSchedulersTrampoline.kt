package com.test.news.base

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulersTrampoline : SchedulersProvider {
    override fun getUiScheduler(): Scheduler = Schedulers.trampoline()
    override fun getIoScheduler(): Scheduler = Schedulers.trampoline()
    override fun getComputationScheduler(): Scheduler = Schedulers.trampoline()
}
