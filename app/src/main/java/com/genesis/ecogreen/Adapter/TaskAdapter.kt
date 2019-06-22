package com.genesis.ecogreen.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.genesis.ecogreen.Detail.DetailTasks
import com.genesis.ecogreen.R
import com.genesis.ecogreen.Task
import kotlinx.android.synthetic.main.cardview_tasks.view.*

class TaskAdapter(var task : List<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tasks, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = task.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(task[position])
        holder.setOnClickListener()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var context: Context = itemView.context

        fun setOnClickListener() {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            var intent = Intent(context, DetailTasks ::class.java)
            intent.putExtra("nombre", v.task_name.text.toString())

            context.startActivity(intent)
        }

        fun bind(item: Task) = with(itemView) {

            Glide.with(itemView.context)
                .load(item.image)
                //.placeholder(R.drawable.ic_launcher_background)
                .into(task_logo)

            task_name.text = item.nombre
        }
    }
}