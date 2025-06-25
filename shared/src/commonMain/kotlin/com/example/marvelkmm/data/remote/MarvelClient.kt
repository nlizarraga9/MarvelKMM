package com.example.marvelkmm.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

class MarvelCharactersClient(
    private val client: HttpClient
) {
    suspend fun getAllCharacters(
        timestamp: Long, md5: String, publicKey: String
    ): CharactersResponse {
        return client.get("https://gateway.marvel.com/v1/public/characters") {
            headers {
                append(HttpHeaders.Accept, "application/json")
            }
            parameter("ts", timestamp)
            parameter("hash", md5)
            parameter("apikey", publicKey)
        }.body()
    }
}