package com.example.presentation.components.screens

import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.RedditPostsInteractor
import com.example.utils.BaseViewModel
import com.example.presentation.data.HomeUiState
import com.example.presentation.data.PostItemState
import com.example.presentation.mapper.PostItemStateMapper
import com.example.presentation.mapper.RedditPostsModelsMapper
import com.example.presentation.navigation.RedditPostsRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val router: RedditPostsRouter,
    private val redditPostsInteractor: RedditPostsInteractor,
    private val redditPostsModelsMapper: RedditPostsModelsMapper,
    private val postItemStateMapper: PostItemStateMapper,
) : BaseViewModel() {
    // region UI Binding
    val state = MutableLiveData<HomeUiState>(HomeUiState.NoPosts(false, listOf(), "")).asNonMutable()

    fun onSelectPost(postItemState: PostItemState) {
        router.postDetails(postItemStateMapper.map(postItemState))
    }
    // endregion

    // region State
    suspend fun refresh() {
        val posts = redditPostsInteractor.getTopPosts()
        state.setValue(redditPostsModelsMapper.map(posts))
    }
    // endregion
}