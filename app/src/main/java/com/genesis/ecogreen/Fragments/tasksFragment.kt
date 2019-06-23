package com.genesis.ecogreen.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genesis.ecogreen.Adapter.taskAdapter

import com.genesis.ecogreen.R
import com.genesis.ecogreen.Task
import com.genesis.ecogreen.databinding.FragmentTaskBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.cardview_tasks.view.*

class tasksFragment : Fragment() {
/*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=DataBindingUtil.inflate<FragmentTaskBinding>(inflater,R.layout.fragment_task,container,false)
        return binding.root
    }
*/

    interface item {
        fun nombreItem(nombre: String)
    }

    lateinit var rvtask: RecyclerView
    val taskList: ArrayList<Task> = ArrayList()
    var itemclick: item? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_task, container, false)

        rvtask = view.findViewById(R.id.taskRV)

        var adapterF = taskAdapter(taskList, object : taskAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View) {
                itemclick?.nombreItem(view.task_name.text.toString())
            }

        })
        rvtask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterF
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
                adapterF.notifyDataSetChanged()
            }

        })

        return view
    }

    override fun onAttach(context: Context) {
        itemclick = context as item
        super.onAttach(context)
    }

}
