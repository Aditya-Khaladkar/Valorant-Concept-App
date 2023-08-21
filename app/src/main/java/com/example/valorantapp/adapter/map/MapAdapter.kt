package com.example.valorantapp.adapter.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valorantapp.R
import com.example.valorantapp.adapter.weapon.WeaponAdapter
import com.example.valorantapp.model.map.MapData
import com.example.valorantapp.model.weapon.WeaponData
import kotlin.coroutines.coroutineContext

class MapAdapter(private var list: List<MapData>): RecyclerView.Adapter<MapAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val displayName: TextView = itemView.findViewById(R.id.txtDisplayName)
        val displayIcon: ImageView = itemView.findViewById(R.id.imageView)
        val displayCord: TextView = itemView.findViewById(R.id.txtDisplaycoordinates)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.map_layout, parent, false)
        return ViewHolder(view)
    }

    fun setFilteredList(mapList: List<MapData>){
        this.list = mapList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val map = list[position]
        holder.displayName.text = map.displayName
        holder.displayCord.text = map.displayCoordinate
        Glide.with(holder.displayIcon.context)
            .load(map.displayIcon)
            .into(holder.displayIcon)

    }
}