package com.example.valorantapp.adapter.agent

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.valorantapp.R
import com.example.valorantapp.api.AgentApi
import com.example.valorantapp.api.URL
import com.example.valorantapp.databinding.ActivityDashboardBinding
import com.example.valorantapp.model.agent.AgentData
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView

class AgentAdapter(
    private val binding: ActivityDashboardBinding,
    private val context: Context,
    private val list: List<AgentData>
) :
    RecyclerView.Adapter<AgentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profile_image = itemView.findViewById<CircleImageView>(R.id.profile_image)
        val display_name = itemView.findViewById<TextView>(R.id.display_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.agent_pic_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agent = list[position]
        Glide.with(holder.profile_image.context)
            .load(agent.displayIcon)
            .into(holder.profile_image)
        holder.display_name.text = agent.displayNames

        holder.profile_image.setOnClickListener {
            Log.d("@debug", "onBindViewHolder: ${agent.uuid}")

            AgentApi().fetchAgentData(
                context,
                URL.AGENT_DATA_URL + agent.uuid,
                binding
            )
        }
    }
}