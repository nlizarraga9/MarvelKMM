package com.example.marvelkmm.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this, CharactersViewModelFactory(this))[CharactersViewModel::class.java]

        setContent {
            MaterialTheme {
                CharacterScreen(viewModel)
            }
        }
    }
}