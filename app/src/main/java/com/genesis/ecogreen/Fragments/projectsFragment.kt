package com.genesis.ecogreen.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ToolbarBindingAdapter

import com.genesis.ecogreen.R
import com.genesis.ecogreen.databinding.FragmentProjectBinding
import com.google.firebase.auth.FirebaseAuth

class projectsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=DataBindingUtil.inflate<FragmentProjectBinding>(inflater,R.layout.fragment_project,container,false)
        val mAuth = FirebaseAuth.getInstance()

        Toast.makeText(this.context,mAuth.currentUser?.email.toString(),Toast.LENGTH_LONG).show()
        return binding.root
    }
}
