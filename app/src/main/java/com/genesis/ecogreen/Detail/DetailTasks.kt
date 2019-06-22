package com.genesis.ecogreen.Detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import com.genesis.ecogreen.R
import com.google.firebase.database.*

class DetailTasks : AppCompatActivity() {

    var context: Context = this

    var image: String? =null
    var desc: String? =null
    var hecho: String? =null
    var nombre: String? =null

    var detailDesc : TextView? = null
    var detailHecho : TextView? = null
    var detailNombre : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tasks)

        nombre = intent.getStringExtra("nombre")

        detailNombre = findViewById(R.id.detailNombre)
        detailDesc = findViewById(R.id.detailDesc)
        detailHecho = findViewById(R.id.detailHecho)


        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("ecogreen-cd87f").child(nombre.toString())

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var image: String = p0.child("image").getValue().toString()
                Log.d("datos", "0 " + image)
                Glide.with(context.applicationContext)
                    .load(p0.child("image").getValue().toString())

                var nombre: String = p0.child("nombre").getValue().toString()
                Log.d("datos", "1 " + nombre)
                context.apply {
                    detailNombre?.text = nombre
                }
                var descripcion: String = p0.child("desc").getValue().toString()
                Log.d("datos", "2 " + descripcion)
                detailDesc?.text = descripcion
                var hecho: String = p0.child("hecho").getValue().toString()
                detailHecho?.text = hecho
            }

        })

    }

    fun llenar(fund: String){

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("ecogreen-cd87f").child(fund)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var image: String = p0.child("image").getValue().toString()

                var nombre: String = p0.child("nombre").getValue().toString()
                Log.d("datos", "1 " + nombre)
                context.apply {
                    detailNombre?.text = nombre
                }
                var descripcion: String = p0.child("desc").getValue().toString()
                Log.d("datos", "2 " + descripcion)
                detailDesc?.text = descripcion
                var hecho: String = p0.child("hecho").getValue().toString()
                Log.d("datos", "3 " + hecho)
                detailHecho?.text = hecho
            }

        })
    }
}
