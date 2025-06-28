package com.example.marvelkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModelProvider
import com.example.marvelkmm.android.ui.screens.CharacterScreen
import com.example.marvelkmm.android.ui.viewmodels.CharactersViewModel
import com.example.marvelkmm.android.ui.viewmodels.CharactersViewModelFactory

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