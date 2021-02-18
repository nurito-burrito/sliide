package com.test.news.newsScreen

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.test.news.common.Launch
import com.test.news.features.news.presentation.NewsActivity
import org.junit.Rule
import org.junit.Test


class NewsScreenTests : Launch {
    @get:Rule
    var activityTestRule = ActivityTestRule<NewsActivity>(NewsActivity::class.java)

    override fun launch(intent: Intent?) {
        activityTestRule.launchActivity(Intent())
    }

    /**
     * Given: the user successfully logged in to the app
     * When: there is internet connection
     * Then: images are displayed in the rows on the list (row can have one or more images
     * scrollable horizontally)
     **/
    @Test
    fun checkImagesAreDisplayedInRows() {
        news(this) {
        } result {
            imageIsVisible()
        }
    }

    /**
     * Given: the news images are successfully loaded on the screen
     * When: the user clicks one of the image
     * Then: user is navigated to the external browser with clicked image loaded
     **/
    @Test
    fun userIsNavigatedToTheExternalBrowser() {
        news(this) {
            tapOnFirstImageInList()
        } result {
            browserIsShown()
        }
    }
}
