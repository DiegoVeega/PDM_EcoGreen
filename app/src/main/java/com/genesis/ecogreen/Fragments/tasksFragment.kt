package com.genesis.ecogreen.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genesis.ecogreen.Adapter.taskAdapterBryan

import com.genesis.ecogreen.R
import com.genesis.ecogreen.Models.Task
import com.genesis.ecogreen.databinding.FragmentTaskBinding
import com.google.firebase.database.*

class tasksFragment : Fragment() {

    lateinit var rvtask: RecyclerView
    val taskList: ArrayList<Task> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:FragmentTaskBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_task, container, false)

        rvtask = view.taskRV


        var adapterx=taskAdapterBryan(this.context)

        rvtask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterx
            setHasFixedSize(true)
        }

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("Retos")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                taskList.removeAll(taskList)
                for (snapshot: DataSnapshot in p0.children) {
                    var task: Task = snapshot.getValue(Task::class.java)!!
                    taskList.add(task)
                }
                adapterx.setMatches(taskList)
            }

        })

        return view.root
    }



}
