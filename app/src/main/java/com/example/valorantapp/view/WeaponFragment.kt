package com.example.valorantapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valorantapp.R
import com.example.valorantapp.api.AgentApi
import com.example.valorantapp.api.WeaponApi
import com.example.valorantapp.databinding.FragmentWeaponBinding

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

        WeaponApi().fetchWeaponList(requireContext(), binding)

        return binding.root
    }


}