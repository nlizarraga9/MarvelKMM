package com.example.marvelkmm.android.ui.screens

import com.example.marvelkmm.domain.model.MarvelCharacter

sealed class ScreenState {
    object Loading : ScreenState()
    class ShowCharacters(val list: List<MarvelCharacter>) : ScreenState()
    class Error(val message: String) : ScreenState()
}