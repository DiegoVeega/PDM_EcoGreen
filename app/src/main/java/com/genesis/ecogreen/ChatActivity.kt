package com.genesis.ecogreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.genesis.ecogreen.Models.ChatMessage
import com.genesis.ecogreen.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat_from_row.view.*
import kotlinx.android.synthetic.main.fragment_chat_from_row.view.textView_from_row
import kotlinx.android.synthetic.main.fragment_to_from_row.view.*
import kotlinx.android.synthetic.main.fragment_users_chats.*

class ChatActivity : AppCompatActivity() {

    companion object{
        val TAG = "Shat"
    }

    val adapter = GroupAdapter<ViewHolder>()

    val toUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_users_chats)

        recyclerView_chats_users.adapter = adapter

        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        supportActionBar?.title = user.username

        ListenForMessages()

        messenger_send_button.setOnClickListener{
            Log.d(TAG, "Enviando mensaje...")
            perfomSendMessage()
        }
    }

    private fun ListenForMessages(){
        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid

        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")

        ref.addChildEventListener(object: ChildEventListener{
            override fun onChildRemoved(p0: DataSnapshot) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)
                if (chatMessage != null){
                    Log.d(TAG, chatMessage.text)

                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid){
                        adapter.add(ChatFromItem(chatMessage.text))
                    }else{
                        adapter.add(ChatToItem(chatMessage.text))
                    }

                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }
        })
    }

    private fun perfomSendMessage() {
        val text = description_message_chat.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        val toId = user.uid

        if (fromId == null) return

        val reference = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()

        val toRefence = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()

        val chatMessage = ChatMessage(reference.key!!, text, fromId, toId, System.currentTimeMillis() / 1000)
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved: ${reference.key}")
                description_message_chat.text.clear()
                recyclerView_chats_users.scrollToPosition(adapter.itemCount -1)
            }

        toRefence.setValue(chatMessage)
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
        return R.layout.fragment_to_from_row
    }
}
