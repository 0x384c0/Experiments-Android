package com.example.presentation.components.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.presentation.mapper.RedditPostsModelsMapper
import com.example.presentation.mock.MockRedditPostsInteractor
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(HomeViewModel(
                redditPostsInteractor = MockRedditPostsInteractor(),
                redditPostsModelsMapper = RedditPostsModelsMapper()
            ))
        }
    }

    @Test
    fun homeScreenHasPosts() {
        TODO("not implemented")
    }

    @Test
    fun homeScreenNoPosts() {
        TODO("not implemented")
    }
}