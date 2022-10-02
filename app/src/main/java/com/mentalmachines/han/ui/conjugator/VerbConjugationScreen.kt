package com.mentalmachines.han.ui.conjugator

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun VerbConjugationScreen(
    viewModel: VerbConjugationViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onAction: (actions: VerbConjugationScreenActions) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        VerbConjugationScreenContent()
    }
}


@Composable
fun VerbConjugationScreenContent(
) {
//    Scaffold(
//        scaffoldState = scaffoldState
//    ) { innerPadding ->
//        DetailScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
//    }
}

sealed class VerbConjugationScreenActions {
    object Back : VerbConjugationScreenActions()
}
