package com.genesis.ecogreen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.genesis.ecogreen.Fragments.projectDetailFragment
import com.genesis.ecogreen.Fragments.projectsFragmentDirections
import com.genesis.ecogreen.Models.Project
import com.genesis.ecogreen.R

class ProjectAdapter internal constructor(
    context: Context?
) : RecyclerView.Adapter<ProjectAdapter.projectViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var projects = emptyList<Project>()

    inner class projectViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val nombre: TextView = itemView.findViewById(R.id.item_name_project)
        val desc: TextView = itemView.findViewById(R.id.item_description_project)

        val objetivo: TextView = itemView.findViewById(R.id.item_objetivo)
        val privado: TextView = itemView.findViewById(R.id.item_privado)

        val item : ConstraintLayout = itemView.findViewById(R.id.item_project)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): projectViewHolder {
        val itemView = inflater.inflate(R.layout.rv_project, parent, false)
        return projectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: projectViewHolder, position: Int) {
        val current = projects[position]

        Glide.with(holder.image.context)
            .load(current.image)
            .into(holder.image)

        holder.nombre.text = current.nombre
        holder.desc.text = current.descripcion
        holder.objetivo.text = current.objetivo
        holder.privado.text = current.privado

        holder.item.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    //Directions
                    projectsFragmentDirections.ActionProjectsFragmentToProjectDetailFragment
                    (current.nombre,current.descripcion,current.objetivo,current.privado)
                )
        }


    }

    internal fun setMatches(projects: List<Project>){
        this.projects = projects
        notifyDataSetChanged()
    }

    override fun getItemCount() = projects.size

}