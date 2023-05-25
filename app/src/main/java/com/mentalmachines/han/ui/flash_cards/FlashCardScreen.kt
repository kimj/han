package com.mentalmachines.han.ui.flash_cards

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FlashCardScreen(
    viewModel: FlashCardViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    // onAction: (actions: FlashCardScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        FlashCardScreenContent(modifier = Modifier.padding(innerPadding))
        // FlashCardScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
    }
}

@Composable
fun FlashCardScreenContent(
    modifier: Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    // onAction: (actions: FlashCardScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        FlashCardScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

sealed class FlashCardScreenActions {
    object Back : FlashCardScreenActions()
}

