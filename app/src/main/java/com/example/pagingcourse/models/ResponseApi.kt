package com.example.pagingcourse.models


import com.google.gson.annotations.SerializedName

data class ResponseApi(
    @SerializedName("results")
    val results: List<RickMorty>
)