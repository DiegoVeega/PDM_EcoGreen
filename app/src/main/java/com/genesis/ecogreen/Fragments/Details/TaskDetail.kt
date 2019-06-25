package com.genesis.ecogreen.Fragments.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide

import com.genesis.ecogreen.R
import com.genesis.ecogreen.databinding.FragmentTaskDetailBinding



class TaskDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTaskDetailBinding>(inflater,R.layout.fragment_task_detail, container, false)
        val args=TaskDetailArgs.fromBundle(arguments!!)
        binding.detailDesc.text=args.desc
        binding.detailNombre.text=args.nombre
        Glide.with(this)
            .load(args.image)
            .into(binding.detailImage)
        
        return binding.root
    }
}
