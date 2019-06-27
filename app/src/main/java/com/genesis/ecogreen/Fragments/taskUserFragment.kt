package com.genesis.ecogreen.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.genesis.ecogreen.R
import com.genesis.ecogreen.databinding.FragmentUserBinding
import com.genesis.ecogreen.databinding.FragmentUserListBinding
import com.genesis.ecogreen.databinding.FragmentUserTaskBinding

class taskUserFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentUserBinding>(inflater, R.layout.fragment_user,
            container, false)
        binding.btnTaskUsers.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_userFragment_to_taskUserFragment)
        }
        return binding.root
    }
}