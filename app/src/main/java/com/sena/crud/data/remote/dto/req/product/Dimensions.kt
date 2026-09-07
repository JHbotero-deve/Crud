package com.sena.crud.data.remote.dto.req.product


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dimensions(
    @param:Json(name = "depth")
    val depth: Double = 0.0,
    @param:Json(name = "height")
    val height: Double = 0.0,
    @param:Json(name = "width")
    val width: Double = 0.0
)