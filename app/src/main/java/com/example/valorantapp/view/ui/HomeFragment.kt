package com.example.valorantapp.view.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.R
import com.example.valorantapp.adapter.agent.AgentAdapter
import com.example.valorantapp.api.URL
import com.example.valorantapp.databinding.ActivityDashboardBinding
import com.example.valorantapp.databinding.FragmentHomeBinding
import com.example.valorantapp.model.agent.AgentData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.agentPicRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.agentPicRecyclerview.setHasFixedSize(true)

        fetchAgentList(requireContext(), binding)

        return binding.root
    }

    private fun fetchAgentList(context: Context, binding: FragmentHomeBinding) {
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
}