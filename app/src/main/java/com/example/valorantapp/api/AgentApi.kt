package com.example.valorantapp.api

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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
                val agentAdapter = AgentAdapter(context, agentsList)
                binding.agentPicRecyclerview.adapter = agentAdapter
            },
            {
                // Handle error
            })
        queue.add(jsonObjectRequest)
    }
}