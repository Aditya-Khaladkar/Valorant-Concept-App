package com.example.valorantapp.api

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.valorantapp.adapter.agent.AgentAdapter
import com.example.valorantapp.databinding.ActivityDashboardBinding
import com.example.valorantapp.model.agent.AgentData
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class AgentApi {

    fun fetchAgentList(context: Context, binding: ActivityDashboardBinding) {
        val queue = Volley.newRequestQueue(context)
        val url = URL.AGENT_URL
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val type = object : TypeToken<List<AgentData>>() {}.type
                val agentsList = gson.fromJson<List<AgentData>>(response.getJSONArray("data").toString(), type)
                val agentAdapter = AgentAdapter(binding, context, agentsList)
                binding.agentPicRecyclerview.adapter = agentAdapter
            },
            {
                // Handle error
            })
        queue.add(jsonObjectRequest)
    }

    fun fetchAgentData(context: Context, url: String, binding: ActivityDashboardBinding) {

        val queue = Volley.newRequestQueue(context)

        Log.d("@debug", "fetchAgentData: $url")

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                // Parse the JSON response using Gson
                val gson = Gson()
                val agentData = gson.fromJson(response.getJSONObject("data").toString(), AgentData::class.java)

                binding.txtAgentName.text = agentData.displayNames

                Glide.with(binding.imgAgentImage)
                    .load(agentData.fullPortrait)
                    .into(binding.imgAgentImage)

                Glide.with(binding.imgAgentBg)
                    .load(agentData.background)
                    .into(binding.imgAgentBg)
            },
            { error ->
                Log.e("UserViewModel", "Error fetching user list: ${error.message}")
            }
        )

        queue.add(request)
    }
}