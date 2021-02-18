package com.test.news.newsScreen

import com.test.news.R
import com.test.news.common.BaseTestRobot
import com.test.news.common.Launch

fun news(func: NewsScreenRobot.() -> Unit) = NewsScreenRobot().apply {
    func()
}

fun news(launch: Launch, func: NewsScreenRobot.() -> Unit) = NewsScreenRobot().apply {
    launch.launch()
    func()
}

class NewsScreenRobot : BaseTestRobot() {
    fun tapOnFirstImageInList() {
        pressItemInList(
            listRes = R.id.recyclerViewNews,
            position = 0
        )
    }

    infix fun result(func: ResultRobot.() -> Unit): ResultRobot {
        return ResultRobot().apply { func() }
    }

    class ResultRobot : BaseTestRobot() {

        fun newsPageIsDisplayed() {
            checkToolbarTitleString("News")
        }

        fun imageIsVisible() {
            imageIsDisplayed(R.id.recyclerViewImageWidget)
        }

        fun browserIsShown() {
            viewIsVisible(R.id.imageView)
        }
    }
}