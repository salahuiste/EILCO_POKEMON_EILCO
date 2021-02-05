package com.example.pokdexcreando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pokdexcreando.models.Generation;
import com.example.pokdexcreando.models.Pokemon;
import com.example.pokdexcreando.models.PokemonRequest;
import com.example.pokdexcreando.pokeapi.CountGeneration;
import com.example.pokdexcreando.pokeapi.PokeapiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PokemonAdapter.OnItemClickListener,GenerationAdapter.OnItemClickListener {
   private Retrofit retrofit;
    private Retrofit Coutretrofit;
    public ArrayList<Pokemon> listpokemons;
    private  PokeapiService service;
    private static final String TAG="POKEDEX";
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewgeneration;
    private  PokeapiService service2;
    private  CountGeneration Countservice;

    private PokemonAdapter pokemonAdapter;
    private GenerationAdapter generationAdapter;
    private int offset;
    private int limit;
    private boolean Paracar;
    public List<Pokemon> dataListitem = new ArrayList<>();
    public  static  final String EXTRA_URL = "imageurl";
    public  static  final String EXTRA_CREATOR="creatorname";
    public  static  final String EXTRA_LIKES="likecount";
    public    ArrayList<Pokemon> listpokemon;
    public   ArrayList<Pokemon> listgeneration;
    public int GEN1;
    public int GEN2;
    public int GEN3;
    public int GEN4;
    public int GEN5;
    public int GEN6;
    public int GEN7;
    public int GEN8;
    public ArrayList<Integer> coutlist= new ArrayList<>();
    public TreeMap<Integer,Integer> couts = new TreeMap<Integer, Integer>();
    List<TreeMap<Integer, Integer>> listcouts = new ArrayList<TreeMap<Integer, Integer>>();


    //void obtenirCount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //generation
        recyclerViewgeneration = findViewById(R.id.rv_generation);
        generationAdapter=new GenerationAdapter(this,this);
        recyclerViewgeneration.setAdapter(generationAdapter);
        //GridLayoutManager layoutManagergeneration= new GridLayoutManager(this,8);
        recyclerViewgeneration.setLayoutManager(new LinearLayoutManager(this
        ,LinearLayoutManager.HORIZONTAL,false));
        //end
       recyclerView= findViewById(R.id.recyler_view);
        pokemonAdapter=new PokemonAdapter(this);

        recyclerView.setAdapter(pokemonAdapter);
        //test profile
       pokemonAdapter.setOnItemClickListener(MainActivity.this);
        //end
        GridLayoutManager layoutManager= new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
     /*  recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
          public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy> 0){
                    int visibmeItemCount=layoutManager.getChildCount();
                    int totalItemCount= layoutManager.getItemCount();
                    int pastVisibleItems= layoutManager.findFirstVisibleItemPosition();
                    if(Paracar){
                        if((visibmeItemCount+pastVisibleItems)>=totalItemCount){
                            Log.d(TAG,"end scrolling");
                            Paracar=false;
                            offset+=20;
                            obtenirDate(offset);

                        }
                    }
                }
            }
        });*/
        Coutretrofit = new Retrofit.Builder().baseUrl("https://pokeapi.glitch.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Paracar=true;
        offset=20;
        //Log.e(TAG,coutlist.get(1));
        //obtenirDate();
       /* ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Post> call = apiInterface.getPost(1);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.d(TAG,"lwalida:"+response.body().toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
    private void obtenirDate(){*/
            obtenirGeneration();
        obtenirCount();

       // Log.e(TAG,String.valueOf(coutlist.get(1)));
        limit=120;
        obtenirDate(limit,offset);


    }

    private void obtenirCount() {
        Countservice = Coutretrofit.create(CountGeneration.class);
        Call<Generation> generationCall = Countservice.obtenircount();
        generationCall.enqueue(new Callback<Generation>() {
            @Override
            public void onResponse(Call<Generation> call, Response<Generation> response) {
                if(response.isSuccessful()){
                    Generation generation= response.body();
                    GEN1= generation.getGen1();
                    GEN2= generation.getGen2();
                    GEN3= generation.getGen3();
                    GEN4= generation.getGen4();
                    GEN5= generation.getGen5();
                    GEN6= generation.getGen6();
                    GEN7= generation.getGen7();
                    GEN8= generation.getGen8();
                    coutlist.add(GEN1);
                    coutlist.add(GEN2);
                    coutlist.add(GEN3);
                    coutlist.add(GEN4);
                    coutlist.add(GEN5);
                    coutlist.add(GEN6);
                    coutlist.add(GEN7);
                    coutlist.add(GEN8);
                    //INTERVAL counts
                    couts.put(0,GEN1);
                    couts.put(GEN1+1,GEN1+GEN2);
                    couts.put(GEN1+GEN2+1,GEN1+GEN2+GEN3);
                    couts.put(GEN1+GEN2+GEN3+1,GEN1+GEN2+GEN3+GEN4);
                    couts.put(GEN1+GEN2+GEN3+GEN4+1,GEN1+GEN2+GEN3+GEN4+GEN5);
                    couts.put(GEN1+GEN2+GEN3+GEN4+GEN5+1,GEN1+GEN2+GEN3+GEN4+GEN5+GEN6);
                    couts.put(GEN1+GEN2+GEN3+GEN4+GEN5+GEN6+1,GEN1+GEN2+GEN3+GEN4+GEN5+GEN6+GEN7);
                    couts.put(GEN1+GEN2+GEN3+GEN4+GEN5+GEN6+1,GEN1+GEN2+GEN3+GEN4+GEN5+GEN6+GEN7);
                    //Log.e(TAG,couts.get(0));
                    listcouts.add(couts);
                    Log.e(TAG,listcouts.toString());
                   // Log.e(TAG,couts.toString());
                    Log.d(TAG,String.valueOf(coutlist));




                }else{
                    Log.d(TAG,"error");

                }
            }

            @Override
            public void onFailure(Call<Generation> call, Throwable t) {
                Log.d(TAG,"error");

            }
        });

    }

    private void obtenirGeneration(){

      service2 = retrofit.create(PokeapiService.class);
      Call <PokemonRequest> pokemonapiservice2= service2.obtenirGeneration();
      pokemonapiservice2.enqueue(new Callback<PokemonRequest>() {
          @Override
          public void onResponse(Call<PokemonRequest> call, Response<PokemonRequest> response) {

              if (response.isSuccessful()){
                  PokemonRequest pokemonRequest = response.body();
                  listgeneration = pokemonRequest.getResults();
               //   Log.d(TAG,"pokemon:"+pokemonRequest.getResults().get(0));
                //  Log.d(TAG,"jawaddd:"+listgeneration.get(0).getName());

                  // pokemonAdapter.addListpokem(listpokemon);
                  generationAdapter.adListGeneration(listgeneration);

              }
              else{
                  Log.d(TAG,"error");

              }

          }

          @Override
          public void onFailure(Call<PokemonRequest> call, Throwable t) {
              Log.d(TAG,"error:"+t.getMessage());

          }
      });



    }
    private void obtenirDate(int limit,int offset){
         service= retrofit.create(PokeapiService.class);

        Call <PokemonRequest> pokemonapiservice= service.obtenirListPokemon(limit,offset);
        pokemonapiservice.enqueue(new Callback<PokemonRequest>() {

            @Override
            public void onResponse(Call<PokemonRequest> call, Response<PokemonRequest> response) {
                Paracar=true;
                // List<Pokemon> list = new ArrayList<Pokemon>();
                //list = response.body().getResults() ;
              //  Log.d(TAG,"lwalida:"+response.body().getResults());

              if (response.isSuccessful()){
                  PokemonRequest pokemonRequest = response.body();
                  listpokemon = pokemonRequest.getResults();
                  Log.d(TAG,"pokemon:"+pokemonRequest.getResults().get(0));

                 // pokemonAdapter.addListpokem(listpokemon);
                  pokemonAdapter.adListPokemon(listpokemon);
              }
                else{
                    Log.d(TAG,"error");

                }
            }

            @Override
            public void onFailure(Call<PokemonRequest> call, Throwable t) {
                Log.d(TAG,"error:"+t.getMessage());

            }
        });


    }

    @Override
    public void onItemClcik(int position) {
        Intent detailtIntent = new Intent( this,DetailActivity.class);
        Pokemon p = listpokemon.get(position);
        //Log.e(TAG, String.valueOf(listpokemon.get(0))+"lwalida");
       detailtIntent.putExtra(EXTRA_URL,p.getUrl());
       detailtIntent.putExtra(EXTRA_CREATOR,p.getName());
       detailtIntent.putExtra(EXTRA_LIKES,p.getNumber());
       startActivity(detailtIntent );
        //Log.e(TAG,"hello jawad"+position);


    }

    @Override
    public void CountClick(int position) {
      //  Intent detailtIntent = new Intent( this,DetailActivity.class);
        //int p = (int) listcouts.get(position).keySet();
        //Log.e(TAG, String.valueOf(p));
       // int j = (int) listcouts.get(position).values().toArray()[position];
         int jvalue= (int) listcouts.get(0).values().toArray()[position];
         int pkey= (int) listcouts.get(0).keySet().toArray()[position];
     //   Log.e(TAG, "hh"+String.valueOf(jvalue)+String.valueOf(pkey));

        //Log.e(TAG, String.valueOf(listpokemon.get(0))+"lwalida");
      //  detailtIntent.putExtra(EXTRA_URL,p.getUrl());
        //    detailtIntent.putExtra(EXTRA_CREATOR,p.getName());
        //detailtIntent.putExtra(EXTRA_LIKES,p.getNumber());
        //   startActivity(detailtIntent );
        obtenirDate(pkey,jvalue);


    }
}