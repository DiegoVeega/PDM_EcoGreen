package com.genesis.ecogreen.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide

import com.genesis.ecogreen.R
import com.genesis.ecogreen.Models.Task2
import com.genesis.ecogreen.databinding.FragmentTaskDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class TaskDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTaskDetailBinding>(inflater,R.layout.fragment_task_detail, container, false)
        val args= TaskDetailArgs.fromBundle(arguments!!)
        val database = FirebaseDatabase.getInstance()
        val mAuth = FirebaseAuth.getInstance()
        var myRef=database.reference

        //RUTA PARA LEER LA TAREA ACTUAL
        var myRef2: DatabaseReference? = database.getReference("Usuario")
            .child(mAuth.currentUser?.uid.toString())
            .child("retoshechos")
            .child(args.nombre)

        binding.detailDesc.text=args.desc
        binding.detailNombre.text=args.nombre
        Glide.with(this)
            .load(args.image)
            .into(binding.detailImage)

        //VERIFICAR SI LA TAREA YA ESTA HECHA
        myRef2?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val taskx: Task2? = p0.getValue(Task2::class.java)
                if(taskx?.done=="Si"){
                    binding.detailHecho.text="Si"
                }else{
                    binding.detailHecho.text="No"
                }
            }

        })

        //TRAER FECHA ACTUAL
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("yyyy / MM / dd ")
        val strDate = mdformat.format(calendar.time)

        //PARA ESCRIBIR EN BASE DE DATOS
        val RutaEscribir=HashMap<String,Any>()
        val task2 = Task2(
            args.desc,
            args.image,
            args.nombre,
            strDate.toString(),
            "Si",
            mAuth.currentUser?.email
        )

        //PARA ACTUALIZAR
        binding.taskCompleteButton.setOnClickListener{
            // Write a message to the database
            Log.d("asdfg","se logra")
            RutaEscribir.put("/Usuario/${mAuth.currentUser?.uid}/retoshechos/${args.nombre}",task2)
            myRef.updateChildren(RutaEscribir)
            binding.detailHecho.text="Si"
        }

        return binding.root
    }
}
