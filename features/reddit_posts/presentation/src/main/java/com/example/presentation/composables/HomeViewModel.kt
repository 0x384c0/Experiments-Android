package com.example.presentation.composables

import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.RedditPostsInteractor
import com.example.presentation.mapper.RedditPostsModelsMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val redditPostsInteractor: RedditPostsInteractor,
    private val redditPostsModelsMapper: RedditPostsModelsMapper,
    ) : BaseViewModel() {
    // region UI Binding
    val state = MutableLiveData<HomeUiState>(HomeUiState.NoPosts(false, listOf(),"")).asNonMutable()
    // endregion

    // region State
    suspend fun refresh() {
        val posts = redditPostsInteractor.getTopPosts()
        state.setValue(redditPostsModelsMapper.map(posts))
    }
    // endregion
}