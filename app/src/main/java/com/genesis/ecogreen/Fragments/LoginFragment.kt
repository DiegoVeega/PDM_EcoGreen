package com.genesis.ecogreen.Fragments


import android.content.Intent
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
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.genesis.ecogreen.databinding.FragmentLoginBinding
import android.widget.ProgressBar
import androidx.annotation.NonNull
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import java.util.*

//import android.R



class LoginFragment : Fragment() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthListener:FirebaseAuth.AuthStateListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        callbackManager = CallbackManager.Factory.create()
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login, container, false)
        mAuth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener {
            registerlogin(it,true)
        }
        binding.loginButton.setOnClickListener{
            registerlogin(it,false)
        }


        /*                          FACEBOOK LOGIN OH ZEE                        */


        binding.facebookButton.setReadPermissions("email", "public_profile")
        binding.facebookButton.fragment = this
        binding.facebookButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("Logueado", "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
                Navigation.findNavController(binding.facebookButton).navigate(R.id.action_loginFragment_to_projectsFragment)
            }

            override fun onCancel() {
                Log.d("cancelado", "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d("erroreado", "facebook:onError", error)
                // ...
            }
        })


        return binding.root


    }

    private fun registerlogin(view: View,boolean: Boolean) {
        val user=username_edit_text
        val pass=password_edit_text

        val email = user.getText().toString().trim()
        val password = pass.getText().toString().trim()
        pbLoading?.visibility = ProgressBar.VISIBLE

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this.context, "Se debe ingresar un email", Toast.LENGTH_LONG).show()
            pbLoading?.visibility=ProgressBar.INVISIBLE
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this.context, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show()
            pbLoading?.visibility=ProgressBar.INVISIBLE

            return
        }


        if(boolean==true){
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this.context, "Usuario registrado con exito",
                            Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_projectsFragment)

                    } else {
                        Toast.makeText(this.context, task.exception?.message,
                            Toast.LENGTH_SHORT).show()
                        pbLoading?.visibility=ProgressBar.INVISIBLE

                    }

                }
        }else{
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this.context, "Bienvenido ",
                            Toast.LENGTH_SHORT).show()
                        // val user = mAuth.currentUser
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_projectsFragment)
                    } else {
                        Toast.makeText(this.context, it.exception?.message,
                            Toast.LENGTH_SHORT).show()
                        pbLoading?.visibility=ProgressBar.INVISIBLE

                    }


                }
        }



    }
    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("feisbus", "handleFacebookAccessToken:$token")
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    Log.d("credencialw", "signInWithCredential:success")
                } else {
                    Toast.makeText(this.context, "Authentication failed.",
                        Toast.LENGTH_LONG).show()
                }

            }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)

    }
}
