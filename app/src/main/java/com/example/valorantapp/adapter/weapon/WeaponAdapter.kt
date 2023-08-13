package com.example.valorantapp.adapter.weapon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valorantapp.R
import com.example.valorantapp.model.weapon.WeaponData

class WeaponAdapter(
    private val list: List<WeaponData>
) : RecyclerView.Adapter<WeaponAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val displayName: TextView = itemView.findViewById(R.id.txtDisplayName)
        val displayIcon: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weapon_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weapon = list[position]
        holder.displayName.text = weapon.displayName
        Glide.with(holder.displayIcon.context)
            .load(weapon.displayIcon)
            .into(holder.displayIcon)

    }
}