package com.example.valorantapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.adapter.agent.AgentAdapter
import com.example.valorantapp.databinding.ActivityDashboardBinding
import com.example.valorantapp.model.agent.AgentData
import com.example.valorantapp.viewmodel.AgentViewModel

class Dashboard : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding
    lateinit var agentViewModel: AgentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.agentPicRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.agentPicRecyclerview.setHasFixedSize(true)

        agentViewModel = ViewModelProvider(this)[AgentViewModel::class.java]


        agentViewModel.getAgent().observe(this, Observer {
            val adapter = AgentAdapter(it)
            binding.agentPicRecyclerview.adapter = adapter
        })

        agentViewModel.fetchAgentsData(this)
    }
}