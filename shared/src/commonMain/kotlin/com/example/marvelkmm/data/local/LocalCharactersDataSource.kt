package com.example.marvelkmm.data.local

import com.example.marvel.AppDatabase
import com.example.marvelkmm.domain.model.MarvelCharacter

class LocalCharactersDataSource (database: AppDatabase) {

    private val queries = database.charactersQueries

    fun saveCharacters(characters: List<MarvelCharacter>) {
        queries.deleteAll()
        characters.forEach {
            queries.insertCharacter(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnailUrl = it.thumbnailUrl
            )
        }
    }

    fun getCachedCharacters(): List<MarvelCharacter> {
        return queries.selectAll().executeAsList().map {
            MarvelCharacter(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnailUrl = it.thumbnailUrl
            )
        }
    }
}