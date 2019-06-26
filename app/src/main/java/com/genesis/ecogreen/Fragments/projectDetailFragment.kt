package com.genesis.ecogreen.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.genesis.ecogreen.Models.Project

import com.genesis.ecogreen.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class projectDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<com.genesis.ecogreen.databinding.FragmentDetailsProjectBinding>(inflater, R.layout.fragment_details_project, container, false)
        val args=projectDetailFragmentArgs.fromBundle(arguments!!)
        val mAuth= FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance()
        var myRef=database.reference

        //TRAER FECHA ACTUAL
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("yyyy / MM / dd ")
        val strDate = mdformat.format(calendar.time)

        val RutaEscribir=HashMap<String,Any>()
        val proyecto = Project(
            args.nombre,
            args.descripcion,
            args.objetivo,
            mAuth.currentUser?.email.toString(),
            strDate,
            null,
            args.privado,
            ""
        )

        binding.projectDetailsName.text=args.nombre
        binding.projectDetailsDescription.text=args.descripcion
        binding.projectDetailsObjetivo.text=args.objetivo
        binding.projectDetailsPrivacy.text=args.privado
        binding.projectDetailsUserCreator.text=mAuth.currentUser?.email
        binding.projectDetailsDate.text=strDate

        RutaEscribir.put("/Proyectos/${args.nombre}/",proyecto)
        myRef.updateChildren(RutaEscribir)

        return binding.root
    }
}
