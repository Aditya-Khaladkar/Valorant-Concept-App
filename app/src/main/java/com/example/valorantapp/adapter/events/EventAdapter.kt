package com.example.valorantapp.adapter.events

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantapp.R
import com.example.valorantapp.adapter.agent.AgentAdapter
import com.example.valorantapp.model.event.EventData

class EventAdapter(private var list: List<EventData>): RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val displayName: TextView = itemView.findViewById(R.id.txtDislayDetails)
        val startTime: TextView = itemView.findViewById(R.id.txtDisplayStart)
        val endTime: TextView = itemView.findViewById(R.id.txtDisplayEnd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = list[position]
        holder.startTime.text = event.startTime
        holder.endTime.text = event.endTime
        holder.displayName.text = event.displayName
    }


}