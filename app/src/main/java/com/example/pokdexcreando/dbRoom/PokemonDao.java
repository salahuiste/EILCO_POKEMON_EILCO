package com.example.pokdexcreando.dbRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokdexcreando.models.Pokemon;

import java.util.List;
@Dao
public interface PokemonDao {
    @Insert
    void insertPokemon(Pokemon pokemon);

    @Query("select * from Pokemon_table")
    List<Pokemon> getPokemon();
}
