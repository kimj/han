package com.mentalmachines.han.ui.conjugator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerbConjugationViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {

    var uiState by mutableStateOf(VerbConjugationViewModelState())
        private set
}

data class VerbConjugationViewModelState(
    val txt: String? = null
)
