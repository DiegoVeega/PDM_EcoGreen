package com.genesis.ecogreen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.genesis.ecogreen.Models.Chat
import com.genesis.ecogreen.R

class chatAdapter internal constructor(
    context: Context?
) : RecyclerView.Adapter<chatAdapter.chatViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var chats = emptyList<Chat>()

    inner class chatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foto: ImageView = itemView.findViewById(R.id.chat_logo)
        val nombre: TextView = itemView.findViewById(R.id.user_name_chat)
        val descrp: TextView = itemView.findViewById(R.id.description_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatViewHolder {
        val itemView = inflater.inflate(R.layout.cardview_chats, parent, false)
        return chatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: chatViewHolder, position: Int) {
        val current = chats[position]

        Glide.with(holder.foto.context)
            .load(current.img)
            .into(holder.foto)

        holder.nombre.text = current.nombre
        holder.descrp.text = current.desp
    }

    internal fun setMatches(tasks: List<Chat>){
        this.chats = tasks
        notifyDataSetChanged()
    }

    override fun getItemCount() = chats.size
}