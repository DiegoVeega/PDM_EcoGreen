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






        binding.projectDetailsName.text=args.nombre
        binding.projectDetailsDescription.text=args.descripcion
        binding.projectDetailsObjetivo.text=args.objetivo
        binding.projectDetailsPrivacy.text=args.privado
        binding.projectDetailsUserCreator.text=args.usuario
        binding.projectDetailsDate.text=args.fecha



        return binding.root
    }
}
