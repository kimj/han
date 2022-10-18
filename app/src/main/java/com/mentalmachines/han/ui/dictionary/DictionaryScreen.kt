package com.mentalmachines.han.ui.dictionary

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FlashCardScreen(
    viewModel: DictionaryViewModel = DictionaryViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onAction: (actions: FlashCardScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        FlashCardScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
    }
}

@Composable
fun FlashCardScreenContent(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onAction: (actions: FlashCardScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        FlashCardScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
    }
}
