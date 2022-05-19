package com.task.rickandmorty.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.rickandmorty.data.networck.dto.EpisodeDto
import com.task.rickandmorty.data.networck.retrofit.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsViewModel(val apiService: APIService) : ViewModel() {

    val episodesLiveData: MutableLiveData<List<EpisodeDto>> = MutableLiveData()

    private lateinit var itemsCall: Call<List<EpisodeDto>>

    fun getEpisodes(ids: String) {
        itemsCall = apiService.getEpisodes(ids)
        itemsCall.enqueue(object : Callback<List<EpisodeDto>> {
            override fun onResponse(
                call: Call<List<EpisodeDto>>,
                response: Response<List<EpisodeDto>>
            ) {
                if (response.isSuccessful) {
                    episodesLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<EpisodeDto>>, t: Throwable) {
            }
        })
    }

    override fun onCleared() {
        itemsCall.cancel()
    }
}