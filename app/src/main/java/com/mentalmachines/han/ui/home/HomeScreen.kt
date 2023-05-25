package com.mentalmachines.han.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class HomeScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onAction: (actions: HomeScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        HomeScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onAction: (actions: HomeScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        HomeScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
    }
}

sealed class HomeScreenActions {
    object Back : HomeScreenActions()
    object Dictionary : HomeScreenActions()
}

