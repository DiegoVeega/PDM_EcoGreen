package com.genesis.ecogreen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.genesis.ecogreen.Fragments.tasksFragmentDirections
import com.genesis.ecogreen.R
import com.genesis.ecogreen.Task
import kotlinx.android.synthetic.main.cardview_tasks.view.*

class taskAdapterBryan internal constructor(
    context: Context?
) : RecyclerView.Adapter<taskAdapterBryan.taskViewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tasks = emptyList<Task>()

    inner class taskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val logo: ImageView = itemView.findViewById(R.id.task_logo)
        val nombre: TextView = itemView.findViewById(R.id.task_name)
        val item: ConstraintLayout = itemView.findViewById(R.id.itemcompleto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder {
        val itemView = inflater.inflate(R.layout.cardview_tasks, parent, false)
        return taskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: taskViewHolder, position: Int) {
        val current = tasks[position]

        Glide.with(holder.logo.context)
            .load(current.image)
            .into(holder.logo)

        holder.nombre.text = current.nombre


        holder.item.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    tasksFragmentDirections.actionTasksFragmentToTaskDetail
                    (current.nombre,current.desc,current.image)
                )
        }


    }

    internal fun setMatches(tasks: List<Task>){
        this.tasks = tasks
        notifyDataSetChanged()
    }


    override fun getItemCount() = tasks.size

}