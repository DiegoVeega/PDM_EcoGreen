package com.genesis.ecogreen.Fragments

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.genesis.ecogreen.Models.Project

import com.genesis.ecogreen.R
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.fragment_details_project.*
import kotlinx.android.synthetic.main.fragment_task_detail.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class projectDetailFragment : Fragment() {
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    private var contentResolverx:ContentResolver?=null
    private var storageReference: StorageReference? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<com.genesis.ecogreen.databinding.FragmentDetailsProjectBinding>(inflater, R.layout.fragment_details_project, container, false)
        val args=projectDetailFragmentArgs.fromBundle(arguments!!)

        storageReference = FirebaseStorage.getInstance().reference
        contentResolverx=context?.contentResolver

        binding.actualizarImage.setOnClickListener { launchGallery() }
        binding.subirImage.setOnClickListener { uploadImage(args.nombre) }



        binding.projectDetailsName.text=args.nombre
        binding.projectDetailsDescription.text=args.descripcion
        binding.projectDetailsObjetivo.text=args.objetivo
        binding.projectDetailsPrivacy.text=args.privado
        binding.projectDetailsUserCreator.text=args.usuario
        binding.projectDetailsDate.text=args.fecha

        Glide.with(binding.proImage.context)
            .load(args.image)
            .into(binding.proImage)



        return binding.root
    }
    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }
    private fun uploadImage(nombreProyecto:String){
        if(filePath != null){
            val ref = storageReference?.child("images/" + UUID.randomUUID().toString())
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
                    addUploadRecordToDb(downloadUri.toString(),nombreProyecto)
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
    private fun addUploadRecordToDb(uri: String,name:String){
        val database = FirebaseDatabase.getInstance()
        var myRef=database.reference
        val RutaEscribir=HashMap<String,Any>()
        RutaEscribir.put("/Proyectos/${name}/image/",uri)
        myRef.updateChildren(RutaEscribir)

        /*val db = FirebaseFirestore.getInstance()

        val data = HashMap<String, Any>()
        data["imageUrl"] = uri

        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this.context, uri, Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this.context, "Error saving to DB", Toast.LENGTH_LONG).show()
            }*/
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
                pro_image.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
