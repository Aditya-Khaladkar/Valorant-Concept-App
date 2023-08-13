package com.example.valorantapp.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.adapter.map.MapAdapter
import com.example.valorantapp.databinding.FragmentMapsBinding
import com.example.valorantapp.databinding.FragmentWeaponBinding
import com.example.valorantapp.model.map.MapData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapApi {
    fun fetchWeaponList(context: Context, binding: FragmentMapsBinding) {
        val queue = Volley.newRequestQueue(context)
        val url = URL.MAP_URL
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val type = object : TypeToken<List<MapData>>() {}.type
                val mapList = gson.fromJson<List<MapData>>(response.getJSONArray("data").toString(), type)
                val mapAdapter = MapAdapter(mapList)
                binding.mapRecyclerView.adapter = mapAdapter
            },
            {
                // Handle error
            })
        queue.add(jsonObjectRequest)
    }
}