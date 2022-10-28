package com.mentalmachines.han.ui.dictionary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    var uiState by mutableStateOf(DictionaryUiState())
        private set
}

data class DictionaryUiState(
    val txt: String? = null
)
