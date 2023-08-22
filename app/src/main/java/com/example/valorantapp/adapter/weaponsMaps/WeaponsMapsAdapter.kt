package com.example.valorantapp.adapter.weaponsMaps

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.valorantapp.view.ui.MapsFragment
import com.example.valorantapp.view.ui.WeaponFragment

internal class WeaponsMapsAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                WeaponFragment()
            }
            1 -> {
                MapsFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}