package com.genesis.ecogreen.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.genesis.ecogreen.R
import com.google.firebase.auth.FirebaseAuth

class NewFragmentProject : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<com.genesis.ecogreen.databinding.FragmentNewProjectBinding>(inflater, R.layout.fragment_new_project, container, false)
        var option:String

        option = if(binding.projectNonPublic.isChecked){
            "si"
        }else{
            "No"
        }



        binding.projectCreateButton.setOnClickListener {
/////////////VALICDACIONES PARA CREACION PROYECTO
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

            if(binding.projectObjetivoInputLayout.error==null &&
            binding.projectDescriptionInputLayout.error==null &&
                binding.projectNameInputLayout.error==null){
                Navigation.findNavController(it)
                    .navigate(
                        NewFragmentProjectDirections
                            .actionNewFragmentProjectToProjectDetailFragment(
                                binding.projectName.text.toString(),
                                binding.projectDescription.text.toString(),
                                binding.projectObjetivo.text.toString(),
                                option
                            )
                    )
            }
        }
        return binding.root
    }
}
