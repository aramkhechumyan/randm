package com.task.rickandmorty.data.networck.dto

import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String
)
