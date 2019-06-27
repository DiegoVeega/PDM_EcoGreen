package com.genesis.ecogreen.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.genesis.ecogreen.R
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.genesis.ecogreen.MainActivity
import kotlin.system.exitProcess




class userFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<com.genesis.ecogreen.databinding.FragmentUserBinding>(inflater, R.layout.fragment_user, container, false)
        binding.signOutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            /*val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP*/

            val intent = Intent(this.context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        binding.btnTaskUsers.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_userFragment_to_taskUserFragment)
        }
        return binding.root
    }


}
