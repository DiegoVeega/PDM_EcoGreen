package com.genesis.ecogreen.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.genesis.ecogreen.R
import com.genesis.ecogreen.databinding.FragmentProjectBinding

class projectsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=DataBindingUtil.inflate<FragmentProjectBinding>(inflater,R.layout.fragment_project,container,false)
        return binding.root
    }


}
