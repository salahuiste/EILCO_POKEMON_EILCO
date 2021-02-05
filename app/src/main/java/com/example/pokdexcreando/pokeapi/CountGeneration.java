package com.example.pokdexcreando.pokeapi;

import com.example.pokdexcreando.models.Generation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountGeneration {

    @GET("/v1/pokemon/counts")
    public Call<Generation> obtenircount();

}
