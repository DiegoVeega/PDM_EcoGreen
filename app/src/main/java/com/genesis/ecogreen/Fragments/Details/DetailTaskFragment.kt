package com.genesis.ecogreen.Fragments.Details

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import androidx.annotation.NonNull
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import android.widget.AdapterView
import android.text.method.TextKeyListener.clear
import android.widget.ArrayAdapter
import android.R
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference









// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailTaskFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DetailTaskFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var ref = FirebaseDatabase.getInstance().reference
    var nombreRef = ref.child("ecogreen-cd87f").child("nombre")
    var descRef = ref.child("ecogreen-cd87f").child("desc")
    var hechoRef = ref.child("ecogreen-cd87f").child("hecho")
    var imageRef = ref.child("ecogreen-cd87f").child("image")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vista :View = inflater.inflate(com.genesis.ecogreen.R.layout.fragment_detail_task,container,false)


        val nombre = vista.findViewById<TextView>(com.genesis.ecogreen.R.id.detailNombre)
        val desc = vista.findViewById<TextView>(com.genesis.ecogreen.R.id.detailDesc)
        val hecho = vista.findViewById<Button>(com.genesis.ecogreen.R.id.detailHecho)
        //val image = vista.findViewById<ImageView>(com.genesis.ecogreen.R.id.image)

        val bundle: Bundle?=arguments

        if(bundle != null){

            val text_nombre = bundle.getString("nombre")
            val text_desc = bundle.getString("descripcion")
            val text_hecho = bundle.getString("No")

            nombre.text = text_nombre
            desc.text = text_desc
            hecho.text = text_hecho

            /*
            nombre.text = nombreRef.toString()
            desc.text = descRef.toString()
            hecho.text = hechoRef.toString()
            image.setImageResource(imageRef.toString().toInt())
            */
        }


        // Inflate the layout for this fragment
        return vista
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }

        nombreRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(String::class.java)
                nombreRef.setValue(value)
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        descRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(String::class.java)
                descRef.setValue(value)
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        hechoRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(String::class.java)
                hechoRef.setValue(value)
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
