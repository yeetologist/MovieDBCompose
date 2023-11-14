package com.github.yeetologist.moviedbcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val name: String,
    val genres: String,
    val description: String,
    val imageUrl: String
) : Parcelable
