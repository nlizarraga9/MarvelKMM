package com.example.marvelkmm.domain.repository

import com.example.marvelkmm.domain.model.MarvelCharacter

interface CharacterRepository {
    suspend fun getCharacters(): List<MarvelCharacter>
    suspend fun getCachedCharacters(): List<MarvelCharacter>

}