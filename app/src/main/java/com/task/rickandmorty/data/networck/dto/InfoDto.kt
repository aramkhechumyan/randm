package com.task.rickandmorty.data.networck.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoDto(
    val next: String
) : Parcelable