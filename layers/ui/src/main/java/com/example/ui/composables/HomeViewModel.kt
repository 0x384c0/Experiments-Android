package com.example.ui.composables

import androidx.lifecycle.MutableLiveData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(private val interactor: TestInteractor) : BaseViewModel() {
    // region UI Binding
    val state = MutableLiveData(HomeViewState("No state")).asNonMutable()
    // endregion

    // region State
    private var counter = 1
    fun changeState() = runBlocking {
        counter = interactor.updateState(counter)
        state.setValue(HomeViewState("state $counter"))
    }
    // endregion
}

interface TestInteractor {
    suspend fun updateState(oldState: Int): Int
}

internal class TestInteractorImpl @Inject constructor() : TestInteractor {
    override suspend fun updateState(oldState: Int): Int {
        return oldState + 1
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class TestModule {
    @Binds
    abstract fun bindTestInteractorImpl(
        testInteractorImpl: TestInteractorImpl
    ): TestInteractor
}

