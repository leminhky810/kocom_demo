package com.minhky.itnews.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.minhky.itnews.ui.component.ItemUser
import com.minhky.itnews.ui.screen.viewmodel.ListUserUIState
import com.minhky.itnews.ui.screen.viewmodel.ListUserViewModel

@Serializable
object ListUserScreen

@Composable
fun ListUserScreen(modifier: Modifier = Modifier,   viewModel: ListUserViewModel = hiltViewModel<ListUserViewModel>()) {

    val listUserUIState by viewModel.listUserState.collectAsStateWithLifecycle()
    Column (modifier.fillMaxSize()) {
        if(listUserUIState is ListUserUIState.Success){
            LazyColumn {
                items((listUserUIState as ListUserUIState.Success).userList) { user ->
                    ItemUser(item = user)
                }
            }
        }

    }
}