package com.example.marvelkmm.android.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.AppDatabase
import com.example.marvelkmm.data.local.DatabaseDriverFactory
import com.example.marvelkmm.data.local.LocalCharactersDataSource
import com.example.marvelkmm.data.remote.MarvelCharactersClient
import com.example.marvelkmm.data.repository.KtorCharactersRepository
import com.example.marvelkmm.data.remote.createKtorClient
import com.example.marvelkmm.data.repository.CharactersService

class CharactersViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val ktorClient = createKtorClient()

        val database = AppDatabase(
            DatabaseDriverFactory(context).createDriver()
        )

        val localDataSource = LocalCharactersDataSource(database)

        val api = MarvelCharactersClient(ktorClient)

        val repository = KtorCharactersRepository(api)

        val service = CharactersService(repository, localDataSource)

        return CharactersViewModel(service) as T
    }
}