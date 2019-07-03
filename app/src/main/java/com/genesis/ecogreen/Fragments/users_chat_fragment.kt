package com.genesis.ecogreen.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.fragment.app.Fragment
import com.genesis.ecogreen.R
import com.genesis.ecogreen.databinding.FragmentUsersChatsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_chat_from_row.view.*
import kotlinx.android.synthetic.main.fragment_chat_to_row.view.*
import kotlinx.android.synthetic.main.fragment_users_chats.*

class users_chat_fragment : Fragment() {

    companion object{
        val TAG = "ChatL"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentUsersChatsBinding>(inflater, R.layout.fragment_users_chats, container, false)

        //setupDummyData()

        listenForMEssages()

        messenger_send_button.setOnClickListener{
            performSendMessage()
        }

        return binding.root
    }

    private fun listenForMEssages(){
        val ref = FirebaseDatabase.getInstance().getReference("/messages")

        ref.addChildEventListener(object: ChildEventListener{
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)
                Log.d(TAG, chatMessage?.text)
            }

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
    }

    class ChatMessage(val id: String, val text: String, val fromId: String, val toId: String, val itemstamp: Long)

    private fun performSendMessage(){
        val text = description_message_chat.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        /*val toId = user.uid

        if (fromId == null) return

        val reference = FirebaseDatabase.getInstance().getReference("/messages").push()

        val chatMessage = ChatMessage(reference.key!!,text, fromId, toId, System.currentTimeMillis() / 1000)
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved: ${reference.key}")
            }*/

    }

    private fun setupDummyData(){
        val adapter = GroupAdapter<ViewHolder>()

        adapter.add(ChatFromItem(""))
        adapter.add(ChatToItem(""))
        adapter.add(ChatFromItem(""))
        adapter.add(ChatToItem(""))

        recyclerView_chats_users.adapter = adapter
    }
}

class ChatFromItem(val text: String): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textView_from_row.text = text
    }

    override fun getLayout(): Int {
        return R.layout.fragment_chat_from_row
    }
}

class ChatToItem(val text: String): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textView_to_row.text = text
    }

    override fun getLayout(): Int {
        return R.layout.fragment_chat_to_row
    }
}