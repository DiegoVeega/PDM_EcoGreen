package com.genesis.ecogreen.Fragments


import android.app.Activity
import android.content.ContentResolver
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
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.genesis.ecogreen.MainActivity
import com.genesis.ecogreen.Models.Project
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.fragment_details_project.*
import kotlinx.android.synthetic.main.fragment_user.*
import java.io.IOException
import java.util.*
import kotlin.system.exitProcess




class userFragment : Fragment() {
    private val PICK_IMAGE_REQUEST = 73
    private var filePath: Uri? = null
    private lateinit var myRef: DatabaseReference
    private var firebaseStore: FirebaseStorage? = null
    private var contentResolverx:ContentResolver?=null
    private var storageReference: StorageReference? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mAuth = FirebaseAuth.getInstance()
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
        val database = FirebaseDatabase.getInstance()
        myRef=database.reference


        binding.correoUser.text = mAuth.currentUser?.email

        myRef.child("/Usuario/${FirebaseAuth.getInstance().currentUser?.uid.toString()}/image/")
            .addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val image: Any? = p0.value
                if(image!=null){
                    Glide.with(binding.userProfilePic.context)
                        .load(image.toString())
                        .into(binding.userProfilePic as ImageView)
                }else if(mAuth.currentUser?.photoUrl!=null){
                    Glide.with(binding.userProfilePic.context)
                        .load(mAuth.currentUser?.photoUrl.toString()+"?height=500")
                        .into(binding.userProfilePic as ImageView)
                }else{
                    binding.userProfilePic.setImageResource(R.drawable.user)
                }
            }

        }
        )

        storageReference = FirebaseStorage.getInstance().reference
        contentResolverx=context?.contentResolver
        binding.editUserPic.setOnClickListener {
            launchGallery()
        }

        return binding.root
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun uploadImage(){
        if(filePath != null){
            val ref = storageReference?.child("users_pics/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    addUploadRecordToDb(downloadUri.toString())
                    Toast.makeText(this.context, "Imagen subida con exito", Toast.LENGTH_LONG).show()

                } else {
                    // Handle failures
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(this.context, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }
    private fun addUploadRecordToDb(uri: String){
        val RutaEscribir= HashMap<String,Any>()
        RutaEscribir.put("/Usuario/${FirebaseAuth.getInstance().currentUser?.uid.toString()}/image/",uri)
        myRef.updateChildren(RutaEscribir)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolverx, filePath)
                val profpic=user_profile_pic as ImageView
                profpic.setImageBitmap(bitmap)
                uploadImage()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}
