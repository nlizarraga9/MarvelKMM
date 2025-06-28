package com.example.marvelkmm.android.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelkmm.android.ui.screens.ScreenState
import com.example.marvelkmm.data.repository.CharactersService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(private val charactersService: CharactersService) : ViewModel() {

    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val screenState: Flow<ScreenState> = _screenState

    fun loadCharacters(){
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
            try {
                val characters = charactersService.getCharacters()

                if (characters.isEmpty()) {
                    _screenState.value = ScreenState.Error("No se encontraron personajes.")
                } else {
                    _screenState.value = ScreenState.ShowCharacters(characters)
                }

            } catch (e: Exception) {
                val cached = charactersService.getCachedCharacters()
                if (cached.isEmpty()) {
                    _screenState.value = ScreenState.Error("Error al cargar datos. Verifique conexión.")
                } else {
                    _screenState.value = ScreenState.ShowCharacters(cached)
                }
            }
        }
    }

    init {
        loadCharacters()
    }

}