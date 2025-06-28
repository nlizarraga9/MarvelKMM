package com.example.marvelkmm.android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvelkmm.android.ui.viewmodels.CharactersViewModel
import com.example.marvelkmm.android.ui.component.CharacterCard

@Composable
fun CharacterScreen (viewModel: CharactersViewModel) {
    val state by viewModel.screenState.collectAsState(initial = ScreenState.Loading)

    when (state) {
        ScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ScreenState.ShowCharacters -> {
            val characters = (state as ScreenState.ShowCharacters).list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(characters.size) { index ->
                    val marvelCharacter = characters[index]
                    CharacterCard(marvelCharacter)
                }
            }
        }

        is ScreenState.Error -> {
            val message = (state as ScreenState.Error).message
            ErrorScreen(
                message = message,
                onRetry = { viewModel.loadCharacters()}
            )
        }
    }
}