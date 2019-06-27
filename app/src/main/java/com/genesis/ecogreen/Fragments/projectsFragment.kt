package com.genesis.ecogreen.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genesis.ecogreen.Adapter.ProjectAdapter
import com.genesis.ecogreen.Models.Project

import com.genesis.ecogreen.R
import com.genesis.ecogreen.databinding.FragmentProjectBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class projectsFragment : Fragment() {

    lateinit var rvproject: RecyclerView
    val projectList: ArrayList<Project> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=DataBindingUtil.inflate<FragmentProjectBinding>(inflater,R.layout.fragment_project,container,false)
        val mAuth = FirebaseAuth.getInstance()
        binding.addProject.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_projectsFragment_to_newFragmentProject)
        }
        binding.rv1.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_projectsFragment_to_projectDetailFragment)
        }
        Toast.makeText(this.context,mAuth.currentUser?.email,Toast.LENGTH_LONG).show()


        rvproject = binding.rv1


        var adapterx=ProjectAdapter(this.context)

        rvproject.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterx
            setHasFixedSize(true)
        }

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("Proyectos")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                projectList.removeAll(projectList)
                for (snapshot: DataSnapshot in p0.children) {
                    var project: Project = snapshot.getValue(Project::class.java)!!
                    projectList.add(project)
                }
                adapterx.setMatches(projectList)
            }

        })

        return binding.root
    }

}
