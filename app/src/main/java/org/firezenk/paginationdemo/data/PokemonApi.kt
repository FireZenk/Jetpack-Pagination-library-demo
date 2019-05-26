package org.firezenk.paginationdemo.data

import me.sargunvohra.lib.pokekotlin.client.PokeApi
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import org.firezenk.paginationdemo.domain.PokemonModel

class PokemonApi {

    private val pokeApi: PokeApi = PokeApiClient()

    fun list(offset: Int = 0, limit: Int = 20): List<PokemonModel> {
        return pokeApi.getPokemonList(offset, limit).results.map {
            search(it.id)
        }
    }

    fun search(pokemonId: Int): PokemonModel {
        val pokemon = pokeApi.getPokemon(pokemonId)
        return PokemonModel(pokemon.id, pokemon.name, pokemon.sprites.frontDefault)
    }
}