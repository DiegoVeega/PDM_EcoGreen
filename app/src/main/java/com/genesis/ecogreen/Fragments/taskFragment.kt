package com.genesis.ecogreen.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.genesis.ecogreen.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_task.*

class taskFragment : Fragment() {

    fun recibir(nombre: String) {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("ecogreen-cd87f").child(nombre)
        myRef.addValueEventListener( object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var nombre: String = p0.getValue().toString()
                tv_detailNombre.text = nombre
                var descripcion: String = p0.getValue().toString()
                tv_detailDesc.text = descripcion
                var hecho: String = p0.getValue().toString()
                tv_detailHecho.text = hecho

            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<com.genesis.ecogreen.databinding.FragmentTaskBinding>(inflater, R.layout.fragment_task, container,false)
        return binding.root

    }


}
