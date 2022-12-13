package com.example.presentation.components.screen

import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.RedditPostsInteractor
import com.example.utils.BaseViewModel
import com.example.presentation.data.HomeUiState
import com.example.presentation.mapper.RedditPostsModelsMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val redditPostsInteractor: RedditPostsInteractor,
    private val redditPostsModelsMapper: RedditPostsModelsMapper,
) : BaseViewModel() {
    // region UI Binding
    val state = MutableLiveData<HomeUiState>(HomeUiState.NoPosts(false, listOf(), "")).asNonMutable()
    // endregion

    // region State
    suspend fun refresh() {
        val posts = redditPostsInteractor.getTopPosts()
        state.setValue(redditPostsModelsMapper.map(posts))
    }
    // endregion
}