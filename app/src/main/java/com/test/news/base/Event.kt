package com.test.news.base

data class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? = if (!hasBeenHandled) {
        hasBeenHandled = true
        content
    } else {
        null
    }
}
