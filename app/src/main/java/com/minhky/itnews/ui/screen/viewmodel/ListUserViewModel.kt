package com.minhky.itnews.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.minhky.itnews.database.model.UserEntity
import com.minhky.itnews.domain.FetchListUserUseCase
import com.minhky.itnews.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListUserViewModel @Inject constructor(
    private val fetchListUserUseCase: FetchListUserUseCase,
    pager: Pager<Int,UserEntity>
) :
    ViewModel() {
    val userPaging = fetchListUserUseCase.invoke().cachedIn(viewModelScope)
    //val userPaging = fetchListUserUseCase.invoke().cachedIn(viewModelScope)

}

sealed interface ListUserUIState {

    data class Success(
        val userList:
        PagingData<User>,
    ) : ListUserUIState

    data object Loading : ListUserUIState


    data object LoadFailed : ListUserUIState


    data object NotShown : ListUserUIState


}
