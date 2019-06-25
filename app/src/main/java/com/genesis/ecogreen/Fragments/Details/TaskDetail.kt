package com.genesis.ecogreen.Fragments.Details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide

import com.genesis.ecogreen.R
import com.genesis.ecogreen.Task2
import com.genesis.ecogreen.databinding.FragmentTaskDetailBinding
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class TaskDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTaskDetailBinding>(inflater,R.layout.fragment_task_detail, container, false)
        val args=TaskDetailArgs.fromBundle(arguments!!)
        binding.detailDesc.text=args.desc
        binding.detailNombre.text=args.nombre
        Glide.with(this)
            .load(args.image)
            .into(binding.detailImage)
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("yyyy / MM / dd ")
        val strDate = mdformat.format(calendar.getTime())
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()

        val awsdwq=HashMap<String,Any>()


        var myRef=database.reference
        val task2 = Task2(args.desc,
            args.image,
            args.nombre,
            strDate.toString(),
            "Si"
        )


        binding.taskCompleteButton.setOnClickListener{
            // Write a message to the database
            Log.d("asdfg","se logra")
            awsdwq.put("/Usuario/retoshechos/${args.nombre}",task2)
            myRef.updateChildren(awsdwq)
        }
        return binding.root
    }
}
