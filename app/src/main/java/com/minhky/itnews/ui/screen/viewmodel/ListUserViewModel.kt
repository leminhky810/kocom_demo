package com.minhky.itnews.ui.screen.viewmodel

import androidx.compose.runtime.snapshots.SnapshotApplyResult.Success
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.domain.FetchListUserUseCase
import com.minhky.itnews.network.model.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListUserViewModel @Inject constructor(private val fetchListUserUseCase: FetchListUserUseCase) : ViewModel() {
    private val _state  : MutableStateFlow<ListUserUIState> = MutableStateFlow(ListUserUIState.Loading)
    val state: StateFlow<ListUserUIState> = _state.asStateFlow()
    init {

        test()
    }


    fun test() {
        viewModelScope.launch {
                fetchListUserUseCase.invoke(30, 100).collect {
                    _state.value = ListUserUIState.Success(it)
                }

        }
    }
//    val listUserUIState= fetchListUserUseCase.invoke(30,100)
//        .map{
//            ListUserUIState.Success(it)
//        }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = ListUserUIState.Loading
//        )

}
sealed interface ListUserUIState {

    data class Success(
        /**
         * The list of news resources contained in this feed.
         */
        val userList: List<UserEntity>,
    ) : ListUserUIState

    data object Loading : ListUserUIState


    data object LoadFailed : ListUserUIState


    data object NotShown : ListUserUIState



}
