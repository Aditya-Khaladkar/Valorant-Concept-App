package com.example.valorantapp.view

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
import com.example.valorantapp.adapter.weapon.WeaponAdapter
import com.example.valorantapp.api.URL
import com.example.valorantapp.databinding.FragmentWeaponBinding
import com.example.valorantapp.model.weapon.WeaponData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeaponFragment : Fragment() {
    lateinit var binding: FragmentWeaponBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWeaponBinding.inflate(layoutInflater, container, false)

        binding.weaponRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.weaponRecyclerview.setHasFixedSize(true)

        fetchWeaponList(requireContext(), binding)

        return binding.root
    }

    private fun fetchWeaponList(context: Context, binding: FragmentWeaponBinding) {
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