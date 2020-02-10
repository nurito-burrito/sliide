package com.test.news.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 *  Base ViewModel class implementing MVI flow:
 *
 *  view(model(intent()))
 *
 *  what in the following class corresponds to:
 *
 *  reduceState(handleIntent(dispatchIntent()))
 *
 *  @param Intent describes user intentions. It is an input value for model
 *  @param Result is the result of model and represents input value for view
 *  @param ViewState represents view state which is rendered to the user
 */
abstract class BaseViewModel<Result, ViewState, Intent>(schedulersProvider: SchedulersProvider) : ViewModel() {

    private val disposable: Disposable
    private val intentsRelay = BehaviorRelay.create<Intent>()
    private var isInitialIntentDispatched = false
    val viewState = MutableLiveData<ViewState>()

    init {
        disposable = intentsRelay
            .throttleFirst(VIEW_TOUCH_DELAY_MS, TimeUnit.MILLISECONDS, schedulersProvider.getComputationScheduler())
            .observeOn(schedulersProvider.getIoScheduler())
            .flatMap { handleIntent(it) }
            .observeOn(schedulersProvider.getUiScheduler())
            .subscribe({ viewState.value = reduceViewState(it) }, { Timber.e(it) })
    }

    override fun onCleared() {
        disposable.dispose()
    }

    /**
     * Should be called from the View with an intent
     */
    fun dispatchIntent(intent: Intent) {
        intentsRelay.accept(intent)
    }

    /**
     * Dispatching intent with this method assures that
     * intent will be dispatched only once across view model lifecycle
     */
    fun dispatchInitialIntent(intent: Intent) {
        if (isInitialIntentDispatched.not()) {
            isInitialIntentDispatched = true
            dispatchIntent(intent)
        }
    }

    protected abstract fun handleIntent(intent: Intent): Observable<Result>

    protected abstract fun reduceViewState(result: Result): ViewState?

    companion object {
        private const val VIEW_TOUCH_DELAY_MS = 500L
    }
}
