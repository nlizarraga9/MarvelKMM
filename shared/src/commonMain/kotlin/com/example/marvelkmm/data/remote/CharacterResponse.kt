package com.example.marvelkmm.data.remote

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CharactersResponse(
    @SerialName("data") val characters: CharacterData
)

@Serializable
data class CharacterData(
    @SerialName("results")
    val list: List<CharacterResult>
)

@Serializable
data class CharacterResult(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("thumbnail") val thumbnail: Thumbnail
)

@Serializable
data class Thumbnail(
    @SerialName("path") val path: String,
    @SerialName("extension") val extension: String
) {
    fun toUrl() : String {
        val securePath = if (path.startsWith("http://")) {
            path.replaceFirst("http://", "https://")
        } else path

        return "$securePath.$extension"
    }
}