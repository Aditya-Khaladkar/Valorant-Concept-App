package com.example.valorantapp.model.event

import com.google.gson.annotations.SerializedName

data class EventData (
    @SerializedName("displayName") val displayName: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String
    )