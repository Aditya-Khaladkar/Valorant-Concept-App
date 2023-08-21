package com.example.valorantapp.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.adapter.map.MapAdapter
import com.example.valorantapp.api.URL
import com.example.valorantapp.databinding.FragmentMapsBinding
import com.example.valorantapp.model.map.MapData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale

class MapsFragment : Fragment() {

    lateinit var binding: FragmentMapsBinding
    lateinit var mapList :List<MapData>
    lateinit var mapAdapter: MapAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMapsBinding.inflate(layoutInflater,container,false)
        binding.mapRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        fetchMapList(requireContext(), binding)
        // Inflate the layout for this fragment

        binding.idSV.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText?.lowercase(Locale.ROOT))
                return true
            }

        })


        return binding.root
    }

    private fun filterList(query: String?) {
        if (query != null){
            val filteredList = ArrayList<MapData>()
            for (i in mapList){
                if (i.displayName.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }

            if(filteredList.isEmpty()){
                Toast.makeText(context,"No Data Found",Toast.LENGTH_SHORT).show()
            }
            else{
                mapAdapter.setFilteredList(filteredList)
            }

        }
    }


    private fun fetchMapList(context: Context, binding: FragmentMapsBinding) {
        val queue = Volley.newRequestQueue(context)
        val url = URL.MAP_URL
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val type = object : TypeToken<List<MapData>>() {}.type
                mapList = gson.fromJson<List<MapData>>(response.getJSONArray("data").toString(), type)
                mapAdapter = MapAdapter(mapList)
                binding.mapRecyclerView.adapter = mapAdapter
            },
            {
                // Handle error
            })
        queue.add(jsonObjectRequest)
    }
}