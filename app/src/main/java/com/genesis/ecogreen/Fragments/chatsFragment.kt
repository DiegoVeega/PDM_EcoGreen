package com.genesis.ecogreen.Fragments

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genesis.ecogreen.Adapter.chatAdapter
import com.genesis.ecogreen.Models.Chat
import com.genesis.ecogreen.R
import com.genesis.ecogreen.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

class chatsFragment : Fragment() {
    lateinit var rvchat: RecyclerView
    val chatList: ArrayList<Chat> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentChatBinding>(inflater, R.layout.fragment_chat, container, false)

        binding.rvchats.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_chatsFragment_to_users_chat_fragment)
        }

        rvchat = binding.rvchats

        var adapterx= chatAdapter(this.context)

        rvchat.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterx
            setHasFixedSize(true)
        }

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var myRef: DatabaseReference = database.getReference("Usuario")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                chatList.removeAll(chatList)
                for (snapshot: DataSnapshot in p0.children) {
                    var chat: Chat = snapshot.getValue(Chat::class.java)!!
                    chatList.add(chat)
                }
                adapterx.setMatches(chatList)
            }

        })

        return binding.root
    }
}
