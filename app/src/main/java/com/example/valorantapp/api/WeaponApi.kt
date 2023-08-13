package com.example.valorantapp.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.adapter.agent.AgentAdapter
import com.example.valorantapp.adapter.weapon.WeaponAdapter
import com.example.valorantapp.databinding.ActivityDashboardBinding
import com.example.valorantapp.databinding.FragmentWeaponBinding
import com.example.valorantapp.model.weapon.WeaponData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeaponApi {

    fun fetchWeaponList(context: Context, binding: FragmentWeaponBinding) {
        val queue = Volley.newRequestQueue(context)
        val url = URL.WEAPON_URL
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val type = object : TypeToken<List<WeaponData>>() {}.type
                val weaponList = gson.fromJson<List<WeaponData>>(response.getJSONArray("data").toString(), type)
                val weaponAdapter = WeaponAdapter(weaponList)
                binding.weaponRecyclerview.adapter = weaponAdapter
            },
            {
                // Handle error
            })
        queue.add(jsonObjectRequest)
    }
}