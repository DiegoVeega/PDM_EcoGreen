package com.genesis.ecogreen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.genesis.ecogreen.Fragments.tasksFragmentDirections
import com.genesis.ecogreen.Models.Task
import com.genesis.ecogreen.Models.Task2
import com.genesis.ecogreen.R

class userTaskAdapter internal constructor(
    context: Context?
) : RecyclerView.Adapter<userTaskAdapter.userTaskViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var userTask = emptyList<Task2>()

    inner class userTaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val logo: ImageView = itemView.findViewById(R.id.task_logo)
        val nombre: TextView = itemView.findViewById(R.id.task_name)
        val fecha: TextView = itemView.findViewById(R.id.task_fesha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userTaskViewHolder {
        val itemView = inflater.inflate(R.layout.fragment_user_task, parent, false)
        return userTaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: userTaskViewHolder, position: Int) {
        val current = userTask[position]

        Glide.with(holder.logo.context)
            .load(current.image)
            .into(holder.logo)

        holder.nombre.text = current.nombre
        holder.fecha.text = current.fecha
    }

    internal fun setMatches(tasks: List<Task2>){
        this.userTask = tasks
        notifyDataSetChanged()
    }

    override fun getItemCount() = userTask.size
}