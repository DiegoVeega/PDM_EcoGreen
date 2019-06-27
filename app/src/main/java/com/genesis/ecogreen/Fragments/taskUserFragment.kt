package com.genesis.ecogreen.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genesis.ecogreen.Adapter.taskAdapterBryan
import com.genesis.ecogreen.Adapter.userTaskAdapter
import com.genesis.ecogreen.Models.Task
import com.genesis.ecogreen.Models.Task2
import com.genesis.ecogreen.R
import com.genesis.ecogreen.databinding.FragmentUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class taskUserFragment : Fragment(){
    val taskList: ArrayList<Task2> = ArrayList()
    lateinit var rvtask: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<com.genesis.ecogreen.databinding.FragmentUserListBinding>(inflater, R.layout.fragment_user_list,
            container, false)
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val usuario=FirebaseAuth.getInstance().currentUser

        rvtask = binding.rv1


        var adapterx= userTaskAdapter(this.context)

        rvtask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterx
            setHasFixedSize(true)
        }

        var myRef: DatabaseReference = database.getReference("Usuario/${usuario?.uid}/retoshechos")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                taskList.removeAll(taskList)
                for (snapshot: DataSnapshot in p0.children) {
                    var task: Task2 = snapshot.getValue(Task2::class.java)!!
                    taskList.add(task)
                }
                adapterx.setMatches(taskList)
            }

        })
        return binding.root
    }
}