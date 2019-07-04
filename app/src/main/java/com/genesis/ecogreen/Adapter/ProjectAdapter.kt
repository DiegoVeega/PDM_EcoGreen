package com.genesis.ecogreen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.genesis.ecogreen.Fragments.projectDetailFragment
import com.genesis.ecogreen.Fragments.projectsFragmentDirections
import com.genesis.ecogreen.Models.Project
import com.genesis.ecogreen.R
import com.google.firebase.auth.FirebaseAuth

class ProjectAdapter internal constructor(
    context: Context?
) : RecyclerView.Adapter<ProjectAdapter.projectViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val contexto=context
    private var projects = emptyList<Project>()

    inner class projectViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val nombre: TextView = itemView.findViewById(R.id.item_name_project)
        val desc: TextView = itemView.findViewById(R.id.item_description_project)
        val objetivo: TextView = itemView.findViewById(R.id.item_objetivo)
        val privado: TextView = itemView.findViewById(R.id.item_privado)
        val creator: TextView = itemView.findViewById(R.id.item_creation_project)

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
        holder.creator.text=current.creador

        holder.item.setOnClickListener {

            if(current.privado=="Si"){
                if(current.creador==FirebaseAuth.getInstance().currentUser?.email){
                    Navigation.findNavController(it)
                        .navigate(
                            //Directions
                            projectsFragmentDirections.actionProjectsFragmentToProjectDetailFragment
                                (current.nombre,current.descripcion,current.objetivo,current.privado,current.fecha.toString(),current.creador.toString(),current.image.toString())
                        )
                }else{
                    Toast.makeText(contexto,"Usted no tiene acceso a este proyecto",Toast.LENGTH_SHORT).show()
                }
            }else{
                Navigation.findNavController(it)
                    .navigate(
                        //Directions
                        projectsFragmentDirections.actionProjectsFragmentToProjectDetailFragment
                            (current.nombre,current.descripcion,current.objetivo,current.privado,current.fecha.toString(),current.creador.toString(),current.image.toString())
                    )
            }

        }


    }

    internal fun setMatches(projects: List<Project>){
        this.projects = projects
        notifyDataSetChanged()
    }

    override fun getItemCount() = projects.size

}