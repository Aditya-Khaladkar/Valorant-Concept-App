package com.example.valorantapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.model.agent.AgentData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AgentViewModel : ViewModel() {

    private val agentArray = MutableLiveData<List<AgentData>>()

    fun getAgent(): LiveData<List<AgentData>> {
        return agentArray
    }

    fun fetchAgentsData(context: Context) {

        val queue = Volley.newRequestQueue(context)
        val url = "https://valorant-api.com/v1/agents"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val type = object : TypeToken<List<AgentData>>() {}.type
                val agentsList = gson.fromJson<List<AgentData>>(response.getJSONArray("data").toString(), type)
                agentArray.value = agentsList
            },
            {
                // Handle error
            })
        queue.add(jsonObjectRequest)
    }
}