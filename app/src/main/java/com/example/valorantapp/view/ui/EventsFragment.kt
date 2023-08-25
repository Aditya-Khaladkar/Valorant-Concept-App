package com.example.valorantapp.view.ui

import android.app.DownloadManager.Request
import android.app.VoiceInteractor
import android.content.Context
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.privacysandbox.tools.core.model.Method
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.R
import com.example.valorantapp.adapter.events.EventAdapter
import com.example.valorantapp.adapter.map.MapAdapter
import com.example.valorantapp.api.URL
import com.example.valorantapp.databinding.AgentPicLayoutBinding
import com.example.valorantapp.databinding.FragmentEvents2Binding
import com.example.valorantapp.databinding.FragmentMapsBinding
import com.example.valorantapp.model.event.EventData
import com.example.valorantapp.model.map.MapData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class EventsFragment : Fragment() {
    lateinit var binding: FragmentEvents2Binding
    lateinit var eventList: List<EventData>
    lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEvents2Binding.inflate(layoutInflater,container,false)
        binding.eventRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        fetchEventList(requireContext(),binding)
        return binding.root
    }

    private fun fetchEventList(context: Context, binding: FragmentEvents2Binding) {
        val queue = Volley.newRequestQueue(context)
        val url = URL.EVENT_URL
        val jsonObjectRequest = JsonObjectRequest(
            com.android.volley.Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val type = object : TypeToken<List<EventData>>() {}.type
                eventList =
                    gson.fromJson<List<EventData>>(response.getJSONArray("data").toString(), type)
                eventAdapter = EventAdapter(eventList)
                binding.eventRecyclerView.adapter = eventAdapter
            },
            {
                // Handle error
            })
        queue.add(jsonObjectRequest)
    }



}