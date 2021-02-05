package com.example.pokdexcreando.pokeapi;

import com.example.pokdexcreando.models.EvolutionDetails;
import com.example.pokdexcreando.models.EvolutionPokemon;
import com.example.pokdexcreando.models.PokemonDetails;
import com.example.pokdexcreando.models.PokemonType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EvolutionService {
 String BASE_URL = "https://pokeapi.glitch.me/v1/";

 @GET("pokemon/{name}")
 Call<List<EvolutionDetails>> getEvolutionByName(@Path("name") String name);
}
