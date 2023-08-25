package com.example.valorantapp.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.valorantapp.R
import com.example.valorantapp.adapter.agent.AgentAdapter
import com.example.valorantapp.api.URL
import com.example.valorantapp.databinding.ActivityDashboardBinding
import com.example.valorantapp.model.agent.AgentData
import com.example.valorantapp.view.ui.EventsFragment
import com.example.valorantapp.view.ui.HomeFragment
import com.example.valorantapp.view.ui.WeaponsMaps
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Dashboard : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        replaceFragment(HomeFragment())


        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.page_maps -> {
                    replaceFragment(WeaponsMaps())
                    true
                }
                R.id.page_events -> {
                    replaceFragment(EventsFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}