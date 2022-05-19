package com.task.rickandmorty.data.networck.retrofit

import com.task.rickandmorty.data.networck.dto.DataDto
import com.task.rickandmorty.data.networck.dto.EpisodeDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("api/character")
    fun getCharacters(@Query("page") page: String): Call<DataDto>

    @GET("api/episode/{ids}")
    fun getEpisodes(@Path("ids") ids: String): Call<List<EpisodeDto>>
}