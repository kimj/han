package com.mentalmachines.han.ui.flash_cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FlashCardViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    var uiState by mutableStateOf(FlashCardUiState())
        private set
}

data class FlashCardUiState(
    val txt: String? = null
)
