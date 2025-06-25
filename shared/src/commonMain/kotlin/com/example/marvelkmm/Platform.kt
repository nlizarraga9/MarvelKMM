package com.example.marvelkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform