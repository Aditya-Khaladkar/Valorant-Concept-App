package com.example.valorantapp.controllers

import android.widget.SearchView
import com.example.valorantapp.databinding.FragmentMapsBinding


class MapSearchController{
    fun search(binding: FragmentMapsBinding){
        binding.idSV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newSearch: String?): Boolean {
                return true
            }

        })
    }
}