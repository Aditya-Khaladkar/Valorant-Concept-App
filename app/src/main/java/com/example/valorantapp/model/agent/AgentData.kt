package com.example.valorantapp.model.agent

import com.google.gson.annotations.SerializedName

data class AgentData(
    @SerializedName("displayIcon") val displayIcon: String,
    @SerializedName("displayName") val displayNames: String,
    @SerializedName("uuid") val uuid: String
)
