package com.genesis.ecogreen.Fragments.Details

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide

import com.genesis.ecogreen.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_task_detail.*
import kotlinx.android.synthetic.main.rv_project.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TaskDetail : Fragment() {

    fun Traer(nombre: String) {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("Retos").child(nombre)
        myRef.addValueEventListener( object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var nombre: String = p0.getValue().toString()
                detailNombre.text = nombre
                var desc: String = p0.getValue().toString()
                detailDesc.text = desc
            }
        })
    }

    private var context = this
    var tv_nombre: TextView? = null
    var tv_desc: TextView? = null
    var nombre: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_task_detail, container, false)

        nombre = arguments?.getString("nombre")
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("Retos").child(nombre!!)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var image: String = p0.child("image").getValue().toString()
                Glide.with(context)
                    .load(p0.child("image").getValue().toString())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(item_image)
                var nombre: String = p0.child("nombre").getValue().toString()
                context.apply {
                    detailNombre?.text = nombre
                }
                var desc: String = p0.child("desc").getValue().toString()
                detailDesc?.text = desc
            }

        })

        tv_nombre = view.findViewById(R.id.detailNombre)
        tv_desc = view.findViewById(R.id.detailDesc)
        return view
    }
}
