package com.genesis.ecogreen

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genesis.ecogreen.Adapter.TaskAdapter
import com.google.firebase.database.*

class tasksList : Fragment() {

    lateinit var rv_tasks: RecyclerView
    var taskList: ArrayList<Task> = ArrayList()

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.task_list, container, false)

        rv_tasks = view.findViewById(R.layout.task_list)

        var adapterF = TaskAdapter(taskList)
        rv_tasks.apply {
            layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            adapter = adapterF
            setHasFixedSize(true)
        }

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("ecogreen-cd87f")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                taskList.removeAll(taskList)
                for (snapshot: DataSnapshot in p0.children) {

                    var task: Task = snapshot.getValue(Task::class.java)!!
                    taskList.add(task)
                }

                adapterF.notifyDataSetChanged()
            }

        })

        return view

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}

