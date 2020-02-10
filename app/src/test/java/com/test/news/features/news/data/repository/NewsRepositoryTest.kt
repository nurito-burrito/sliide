package com.test.news.features.news.data.repository

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryTest {

    @Mock
    private lateinit var newsApi: NewsApi
    private lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        repository = NewsRepository(newsApi)
    }

    @Test
    fun shouldEmitOnlySupportedItems() {

        // when


    }
}
