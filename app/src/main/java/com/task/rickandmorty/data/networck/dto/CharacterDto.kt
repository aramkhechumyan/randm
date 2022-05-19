package com.task.rickandmorty.data.networck.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDto(
    val id: Int,
    val name: String,
    val image: String,
    val episode: List<String>?
) : Parcelable