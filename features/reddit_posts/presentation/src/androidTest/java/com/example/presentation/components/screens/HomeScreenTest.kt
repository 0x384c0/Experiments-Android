package com.example.presentation.components.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.mock.MockRedditPostsInteractor
import com.example.presentation.mapper.RedditPostsModelsMapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockRedditPostsInteractor = MockRedditPostsInteractor()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(HomeViewModel(
                redditPostsInteractor = mockRedditPostsInteractor,
                redditPostsModelsMapper = RedditPostsModelsMapper(),
            ))
        }
    }

    @Test
    fun homeScreenHasPosts(): Unit = runBlocking {
        composeTestRule
            .onNodeWithTag("swipeRefresh")
            .assertIsDisplayed()
            .performTouchInput { swipeDown() }
        composeTestRule.awaitIdle()
        composeTestRule
            .onNodeWithTag("items")
            .onChildren()
            .filter(hasClickAction())
            .onFirst()
            .assertIsDisplayed()
    }

    @Test
    fun homeScreenNoPosts(): Unit = runBlocking {
        mockRedditPostsInteractor.isEmpty = true
        composeTestRule
            .onNodeWithTag("swipeRefresh")
            .assertIsDisplayed()
            .performTouchInput { swipeDown() }
        composeTestRule.awaitIdle()
        composeTestRule
            .onNodeWithTag("no_items")
            .assertIsDisplayed()
    }
}