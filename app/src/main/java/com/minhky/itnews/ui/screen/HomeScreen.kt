package com.minhky.itnews.ui.screen

import android.provider.ContactsContract
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.minhky.itnews.ui.component.Toolbar

@Composable
fun HomeScreen(

    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)

) {

    Column (modifier = modifier) {
        Toolbar()
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = ListUserScreen) {
            composable<ListUserScreen> {
                ListUserScreen()
            }
        }
    }

}
