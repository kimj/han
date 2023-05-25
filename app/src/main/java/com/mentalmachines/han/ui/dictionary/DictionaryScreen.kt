package com.mentalmachines.han.ui.dictionary

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DictionaryScreen(
    viewModel: DictionaryViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    // onAction: (actions: DictionaryScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        DictionaryScreenContent(modifier = Modifier.padding(innerPadding))
        // DictionaryScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
    }
}

@Composable
fun DictionaryScreenContent(
    modifier: Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    // onAction: (actions: DictionaryScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        // DictionaryScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
        DictionaryScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

sealed class DictionaryScreenActions {
    object Back : DictionaryScreenActions()
}
