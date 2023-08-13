package com.example.valorantapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valorantapp.api.MapApi
import com.example.valorantapp.databinding.FragmentMapsBinding


class MapsFragment : Fragment() {

    lateinit var binding: FragmentMapsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMapsBinding.inflate(layoutInflater,container,false)
        binding.mapRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        MapApi().fetchWeaponList(requireContext(), binding)
        // Inflate the layout for this fragment
        return binding.root
    }


}