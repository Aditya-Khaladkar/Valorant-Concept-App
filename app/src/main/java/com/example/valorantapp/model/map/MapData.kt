package com.example.valorantapp.model.map

import com.google.gson.annotations.SerializedName

data class MapData (
    @SerializedName("displayName") val displayName: String,
    @SerializedName("splash") val displayIcon: String,
    @SerializedName("coordinates") val displayCoordinate: String
)