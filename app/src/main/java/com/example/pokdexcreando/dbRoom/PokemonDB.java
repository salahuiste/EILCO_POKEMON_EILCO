package com.example.pokdexcreando.dbRoom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokdexcreando.models.Pokemon;

@Database(entities = Pokemon.class,version = 1)
public abstract class PokemonDB extends RoomDatabase {
    private  static  PokemonDB instance;
    public   abstract PokemonDao pokemonDao();

    public static synchronized PokemonDB getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,PokemonDB.class,"Pokemon_database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
      return  instance;
    }


}
