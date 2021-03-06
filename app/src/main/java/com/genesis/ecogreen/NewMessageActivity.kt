package com.genesis.ecogreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.genesis.ecogreen.Models.User
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_users_chats.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class NewMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat)

        supportActionBar?.title = "Select User"

        fecthUsers()
    }

    companion object{
        val USER_KEY = "USER_KEY"
    }

    private fun fecthUsers(){
        val ref = FirebaseDatabase.getInstance().getReference("/Usuario")
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach{
                    Log.d("NewMessage", it.toString())
                    val user = it.getValue(User::class.java)
                    if(user != null){
                        adapter.add(UserItem(user))
                    }
                }

                adapter.setOnItemClickListener{ item, view ->
                    val userItem = item as UserItem

                    val intent = Intent(view.context, ChatActivity::class.java)
                    //intent.putExtra(USER_KEY, userItem.user.username)
                    intent.putExtra(USER_KEY, userItem.user)
                    startActivity(intent)


                }

                rvchats.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}

class UserItem(val user: User): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textView_new_message.text = user.username
    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }
}
