package com.genesis.ecogreen.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.genesis.ecogreen.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.genesis.ecogreen.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login, container, false)
        mAuth = FirebaseAuth.getInstance()
        binding.registerButton.setOnClickListener {
            register(it)
        }

        return binding.root
    }


    private fun register(view: View) {

        val user=username_edit_text
        val pass=password_edit_text

        val email = user.getText().toString().trim()
        val password = pass.getText().toString().trim()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this.context, "Se debe ingresar un email", Toast.LENGTH_LONG).show()
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this.context, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show()
        }


        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this.context, "Usuario registrado con exito",
                        Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_projectsFragment2)

                } else {
                    Toast.makeText(this.context, task.exception?.message,
                        Toast.LENGTH_SHORT).show()
                }

            }

    }
}
