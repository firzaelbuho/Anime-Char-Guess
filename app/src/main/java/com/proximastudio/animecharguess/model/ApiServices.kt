package com.proximastudio.animecharguess.model

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("api/animechar.json")



    fun getCharacters(): retrofit2.Call<List<Character>>



}