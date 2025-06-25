package com.example.marvelkmm.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)