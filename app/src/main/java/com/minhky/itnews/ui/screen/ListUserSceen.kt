package com.minhky.itnews.ui.screen

import androidx.compose.foundation.layout.Arrangement
import  androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import androidx.hilt.navigation.compose.hiltViewModel
import com.minhky.itnews.ui.component.ItemUser
import com.minhky.itnews.ui.screen.viewmodel.ListUserViewModel

@Serializable
object ListUserScreen

@Composable
fun ListUserScreen(
    modifier: Modifier = Modifier,
    viewModel: ListUserViewModel = hiltViewModel<ListUserViewModel>()
) {

    val listUserUIState = viewModel.userPaging.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(listUserUIState.itemCount){ index ->
            val user = listUserUIState[index]
            ItemUser(item = user!!)
        }
    }
}