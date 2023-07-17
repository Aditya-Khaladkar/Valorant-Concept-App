package com.example.valorantapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.adapter.agent.AgentAdapter
import com.example.valorantapp.databinding.ActivityDashboardBinding
import com.example.valorantapp.model.agent.AgentData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Dashboard : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.agentPicRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.agentPicRecyclerview.setHasFixedSize(true)

        val queue = Volley.newRequestQueue(this)
        val url = "https://valorant-api.com/v1/agents"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val type = object : TypeToken<List<AgentData>>() {}.type
                val agentsList = gson.fromJson<List<AgentData>>(response.getJSONArray("data").toString(), type)
                val agentAdapter = AgentAdapter(agentsList)
                binding.agentPicRecyclerview.adapter = agentAdapter
            },
            {
                // Handle error
            })
        queue.add(jsonObjectRequest)
    }
}