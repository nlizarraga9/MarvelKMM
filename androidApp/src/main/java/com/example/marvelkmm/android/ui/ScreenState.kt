package com.example.marvelkmm.android.ui

import com.example.marvelkmm.domain.model.MarvelCharacter

sealed class ScreenState {

    object Loading : ScreenState()

    class ShowCharacters(val list: List<MarvelCharacter>) : ScreenState()
}