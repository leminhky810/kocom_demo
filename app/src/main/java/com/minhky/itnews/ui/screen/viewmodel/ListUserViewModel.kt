package com.minhky.itnews.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhky.itnews.domain.FetchListUserUseCase
import com.minhky.itnews.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListUserViewModel @Inject constructor(private val fetchListUserUseCase: FetchListUserUseCase) : ViewModel() {
    private val _listUserUIState  : MutableStateFlow<ListUserUIState> = MutableStateFlow(ListUserUIState.Loading)
    val listUserState: StateFlow<ListUserUIState> = this._listUserUIState.asStateFlow()
    init {
        fetchListUser()
    }


    private fun fetchListUser() {
        viewModelScope.launch {
                fetchListUserUseCase.invoke(30, 100).collect {
                    this@ListUserViewModel._listUserUIState.value = ListUserUIState.Success(it)
                }

        }
    }


}
sealed interface ListUserUIState {

    data class Success(
        val userList: List<User>,
    ) : ListUserUIState

    data object Loading : ListUserUIState


    data object LoadFailed : ListUserUIState


    data object NotShown : ListUserUIState



}
