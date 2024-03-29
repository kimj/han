package com.mentalmachines.han.ui.grammar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GrammarScreen(
    viewModel: GrammarViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    // onAction: (actions: GrammarScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        // GrammarScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
        GrammarScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GrammarScreenContent(
    modifier: Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    // onAction: (actions: GrammarScreenActions) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Text("Hi")
    }
}


sealed class GrammarScreenActions {
    object Back : GrammarScreenActions()
}
