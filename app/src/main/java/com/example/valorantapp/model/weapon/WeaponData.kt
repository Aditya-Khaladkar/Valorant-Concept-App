package com.example.valorantapp.model.weapon

import com.google.gson.annotations.SerializedName

data class WeaponData(
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayIcon") val displayIcon: String
)
