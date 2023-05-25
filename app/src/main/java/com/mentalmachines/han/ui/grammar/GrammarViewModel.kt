package com.mentalmachines.han.ui.grammar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mentalmachines.han.data.repository.GrammarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GrammarViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    grammarRepository: GrammarRepository
) :
    ViewModel() {

    var uiState by mutableStateOf(GrammarViewModelState())
        private set
}

data class GrammarViewModelState(
    val txt: String? = null
)
