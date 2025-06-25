package com.example.marvelkmm.data.repository

import com.example.marvelkmm.data.remote.MarvelCharactersClient
import com.example.marvelkmm.domain.model.MarvelCharacter

class KtorCharactersRepository(
    private val client: MarvelCharactersClient
) {
    suspend fun getCharacters(
        timestamp: Long,
        md5: String,
        publicKey: String
    ): List<MarvelCharacter> {
        val response = client.getAllCharacters(timestamp, md5, publicKey
        )

        return response.characters.list.map {
            MarvelCharacter(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnailUrl = it.thumbnail.toUrl()
            )
        }
    }
}