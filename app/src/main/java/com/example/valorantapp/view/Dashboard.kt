package com.example.valorantapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.adapter.agent.AgentAdapter
import com.example.valorantapp.databinding.ActivityDashboardBinding
import com.example.valorantapp.model.agent.AgentData

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
        // default
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val newsJsonArray = response.getJSONArray("data")
                val newsArray = ArrayList<AgentData>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val newsModel = AgentData(
                        newsJsonObject.getString("displayIcon")
                    )
                    newsArray.add(newsModel)
                }
                val adapter = AgentAdapter(newsArray)
                binding.agentPicRecyclerview.adapter = adapter
            },
            {
                Toast.makeText(this, "getRespond() : false", Toast.LENGTH_LONG).show()
            })

        queue.add(jsonObjectRequest)

    }
}