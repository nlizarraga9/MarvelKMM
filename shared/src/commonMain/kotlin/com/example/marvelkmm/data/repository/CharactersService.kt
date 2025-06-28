package com.example.marvelkmm.data.repository

import com.example.marvelkmm.data.local.LocalCharactersDataSource
import com.example.marvelkmm.domain.repository.CharacterRepository
import com.example.marvelkmm.domain.model.MarvelCharacter
import com.example.marvelkmm.utils.PRIVATE_KEY
import com.example.marvelkmm.utils.PUBLIC_KEY
import com.example.marvelkmm.utils.getCurrentTimestamp
import com.example.marvelkmm.utils.md5

class CharactersService(
    private val remote: KtorCharactersRepository,
    private val local: LocalCharactersDataSource
) : CharacterRepository {

    override suspend fun getCharacters(): List<MarvelCharacter> {
        val timestamp = getCurrentTimestamp()
        val hash = md5(timestamp.toString() + PRIVATE_KEY + PUBLIC_KEY)

        return try {
            val characters  = remote.getCharacters(timestamp, hash, PUBLIC_KEY)

            local.saveCharacters(characters)
            sort(characters)
        } catch (e: Exception) {
            val cached = local.getCachedCharacters()
            if (cached.isEmpty()) throw e
            cached
        }
    }

    override suspend fun getCachedCharacters(): List<MarvelCharacter> {
       return local.getCachedCharacters()
    }

    private fun sort(characters: List<MarvelCharacter>): List<MarvelCharacter> {
        return characters.sortedWith(CharacterComparator())
    }

    private class CharacterComparator : Comparator<MarvelCharacter> {
        override fun compare(c1: MarvelCharacter, c2: MarvelCharacter): Int {
            if (c1.description.isEmpty() && c2.description.isEmpty()) {
                return c2.id.compareTo(c1.id)
            }
            if (c1.description.isNotEmpty() && c2.description.isNotEmpty()) {
                return c1.id.compareTo(c2.id)
            }
            if (c1.description.isNotEmpty() && c2.description.isEmpty()) {
                return -1
            }
            return 1
        }

    }
}