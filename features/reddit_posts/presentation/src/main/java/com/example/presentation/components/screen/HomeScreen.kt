package com.example.presentation.components.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.components.host.AppTheme
import com.example.presentation.components.item.PostItem
import com.example.presentation.data.HomeUiState
import com.example.presentation.mapper.RedditPostsModelsMapper
import com.example.presentation.mock.MockRedditPostsInteractor
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen(vm: HomeViewModel = hiltViewModel(),
                        composableScope: CoroutineScope = rememberCoroutineScope()) {
    vm.state.observeAsState().value?.let {
        PostsFeedScreen(
            uiState = it,
            onSelectPost = {},
            onRefreshPosts = { composableScope.launch { vm.refresh() } },
            onErrorDismiss = {},
            homeListLazyListState = rememberLazyListState(),
            snackbarHostState = SnackbarHostState(),
        )
    }
    composableScope.launch { vm.refresh() }
}

@Preview
@Composable
internal fun HomePreview() {
    AppTheme {
        HomeScreen(HomeViewModel(
            redditPostsInteractor = MockRedditPostsInteractor(),
            redditPostsModelsMapper = RedditPostsModelsMapper()
        ))
    }
}

/**
 * The home screen displaying just the article feed.
 */
@Composable
private fun PostsFeedScreen(
    uiState: HomeUiState,
    onSelectPost: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    homeListLazyListState: LazyListState,
    snackbarHostState: SnackbarHostState,
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(false),
        onRefresh = onRefreshPosts,
        content = {
            if (uiState is HomeUiState.HasPosts) {
                LazyColumn(
                    state = homeListLazyListState
                ) {
                    items(uiState.posts) { post ->
                        PostItem(post)
                    }
                }
            }
        },
    )
}