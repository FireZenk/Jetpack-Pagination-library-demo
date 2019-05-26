package org.firezenk.paginationdemo.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import org.firezenk.paginationdemo.data.PokemonApi

class DataFactory(private val pokemonApi: PokemonApi = PokemonApi()) : DataSource.Factory<Int, PokemonModel>() {

    private val mutableLiveData: MutableLiveData<PokemonRepository> = MutableLiveData()

    override fun create(): DataSource<Int, PokemonModel> {
        val pokemonDataSource = PokemonRepository(pokemonApi)
        mutableLiveData.postValue(pokemonDataSource)
        return pokemonDataSource
    }
}

class PokemonRepository(private val pokemonApi: PokemonApi)
    : PageKeyedDataSource<Int, PokemonModel>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PokemonModel>) {

        val pokemonList = pokemonApi.list(0, params.requestedLoadSize)

        callback.onResult(pokemonList, null, params.requestedLoadSize)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonModel>) {

        val pokemonList = pokemonApi.list(params.key, params.requestedLoadSize)

        callback.onResult(pokemonList, params.key + params.requestedLoadSize)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonModel>) {}
}