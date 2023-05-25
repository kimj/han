package com.mentalmachines.han.ui.dictionary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mentalmachines.han.data.repository.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(private val dictionaryRepository: DictionaryRepository) :
    ViewModel() {
    var uiState by mutableStateOf(DictionaryUiState())
        private set
}

data class DictionaryUiState(
    val txt: String? = null
)
