package com.genesis.ecogreen.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.genesis.ecogreen.Models.Project

import com.genesis.ecogreen.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

class NewFragmentProject : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<com.genesis.ecogreen.databinding.FragmentNewProjectBinding>(inflater, R.layout.fragment_new_project, container, false)
        var option:String


        val database = FirebaseDatabase.getInstance()
        var myRef=database.reference


        binding.projectCreateButton.setOnClickListener {
/////////////VALICDACIONES PARA CREACION PROYECTO
            option = if(binding.projectNonPublic.isChecked){
                "Si"
            }else{
                "No"
            }

            if(binding.projectName.text.toString()==""
            ){
                binding.projectNameInputLayout.error="Tiene que ingresar nombre"
            }else{
                binding.projectNameInputLayout.error=null
            }

            if(binding.projectDescription.text.toString()==""){
                binding.projectDescriptionInputLayout.error="Tiene que ingresar descripcion"
            }else{
                binding.projectDescriptionInputLayout.error=null
            }

            if(binding.projectObjetivo.text.toString()==""
            ){
                binding.projectObjetivoInputLayout.error="Tiene que ingresar objetivo"
            }else{
                binding.projectObjetivoInputLayout.error=null
            }


            //TRAER FECHA ACTUAL
            val calendar = Calendar.getInstance()
            val mdformat = SimpleDateFormat("yyyy / MM / dd ")
            val strDate = mdformat.format(calendar.time)
            val user=FirebaseAuth.getInstance().currentUser?.email

            val RutaEscribir=HashMap<String,Any>()

            //NAVEGACION CON VALIDACION
            if(binding.projectObjetivoInputLayout.error==null &&
            binding.projectDescriptionInputLayout.error==null &&
                binding.projectNameInputLayout.error==null){

                val proyecto = Project(
                    binding.projectName.text.toString().trim(),
                    binding.projectDescription.text.toString(),
                    binding.projectObjetivo.text.toString(),
                    user,
                    strDate,
                    option,
                    "https://firebasestorage.googleapis.com/v0/b/ecogreen-cd87f.appspot.com/o/forest.png?alt=media&token=26d8ab28-7a86-4227-ad1f-a43cca9a396e"
                )
                val contexto=this.context
                myRef.child("/Proyectos/${binding.projectName.text.toString()}").addValueEventListener(object :
                    ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val proy: Project? = p0.getValue(Project::class.java)
                        if(proy?.nombre==proyecto.nombre){
                            Toast.makeText(contexto,"Ya existe proyecto con ese nombre",Toast.LENGTH_SHORT).show()

                        }else{
                            RutaEscribir.put("/Proyectos/${binding.projectName.text.toString()}/",proyecto)
                            myRef.updateChildren(RutaEscribir)
                            Navigation.findNavController(it)
                                .navigate(
                                    NewFragmentProjectDirections
                                        .actionNewFragmentProjectToProjectDetailFragment(
                                            binding.projectName.text.toString(),
                                            binding.projectDescription.text.toString(),
                                            binding.projectObjetivo.text.toString(),
                                            option,
                                            strDate,
                                            user.toString(),
                                            "https://firebasestorage.googleapis.com/v0/b/ecogreen-cd87f.appspot.com/o/forest.png?alt=media&token=26d8ab28-7a86-4227-ad1f-a43cca9a396e"
                                        )
                                )
                        }
                    }

                })




            }


        }
        return binding.root
    }
}
