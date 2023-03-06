package com.example.presentation.components.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.domain.mock.MockRedditPostsInteractor
import com.example.domain.model.RedditPostsModel
import com.example.presentation.R
import com.example.presentation.components.items.PostItem
import com.example.presentation.data.HomeUiState
import com.example.presentation.data.PostItemState
import com.example.presentation.mapper.PostItemStateMapper
import com.example.presentation.mapper.RedditPostsModelsMapper
import com.example.presentation.navigation.MockRedditPostsRouterImpl
import com.example.presentation.navigation.RedditPostsRouter
import com.example.presentation.navigation.RedditPostsRouterImpl
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    composableScope: CoroutineScope = rememberCoroutineScope()
) {
    vm.state.observeAsState().value?.let {
        PostsFeedScreen(
            uiState = it,
            onSelectPost = vm::onSelectPost,
            onRefreshPosts = { composableScope.launch { vm.refresh() } },
            onErrorDismiss = {},
            homeListLazyListState = rememberLazyListState(),
            snackBarHostState = SnackbarHostState(),
        )
    }
    LaunchedEffect(composableScope) {
        launch { vm.refresh() }
    }
}

@Preview
@Composable
internal fun HomePreview() {
    HomeScreen(HomeViewModel(
        router = MockRedditPostsRouterImpl(),
        redditPostsInteractor = MockRedditPostsInteractor(),
        redditPostsModelsMapper = RedditPostsModelsMapper(),
        postItemStateMapper = PostItemStateMapper()
    ))
}

/**
 * The home screen displaying just the article feed.
 */
@Composable
private fun PostsFeedScreen(
    uiState: HomeUiState,
    onSelectPost: (PostItemState) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    homeListLazyListState: LazyListState,
    snackBarHostState: SnackbarHostState,
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(false),
        onRefresh = onRefreshPosts,
        modifier = Modifier.testTag("swipeRefresh"),
        content = {
            when (uiState) {
                is HomeUiState.HasPosts ->
                    LazyColumn(
                        state = homeListLazyListState,
                        modifier = Modifier.testTag("items"),
                    ) {
                        items(uiState.posts) { post ->
                            PostItem(
                                state = post,
                                onClick = { onSelectPost(post) }
                            )
                        }
                    }
                is HomeUiState.NoPosts -> Text(
                    text = stringResource(id = R.string.no_data),
                    modifier = Modifier.testTag("no_items"),
                )
            }
        },
    )
}

