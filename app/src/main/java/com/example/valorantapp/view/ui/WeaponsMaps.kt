package com.example.valorantapp.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.valorantapp.adapter.weaponsMaps.WeaponsMapsAdapter
import com.example.valorantapp.databinding.FragmentWeaponsMapsBinding
import com.google.android.material.tabs.TabLayout

class WeaponsMaps : Fragment() {
    lateinit var binding: FragmentWeaponsMapsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWeaponsMapsBinding.inflate(layoutInflater, container, false)

        binding.apply {
            tabLayout.addTab(tabLayout.newTab().setText("Weapons"))
            tabLayout.addTab(tabLayout.newTab().setText("Maps"))
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            val adapter = WeaponsMapsAdapter(requireContext(), childFragmentManager,
                tabLayout.tabCount)
            viewPager.adapter = adapter
            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }

        return binding.root
    }

}