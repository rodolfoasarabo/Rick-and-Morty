package com.example.rickandmorty.data.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenericResponse<T>(
    val info: InfoResponse,
    val results: List<T>
)

@JsonClass(generateAdapter = true)
data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String?
)