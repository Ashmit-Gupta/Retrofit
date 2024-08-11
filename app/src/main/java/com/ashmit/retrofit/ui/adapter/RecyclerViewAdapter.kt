package com.ashmit.retrofit.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ashmit.retrofit.R
import com.ashmit.retrofit.data.models.User
import com.ashmit.retrofit.data.models.UserItem

class RecyclerViewAdapter(private var userList : User) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTV)
        val body: TextView = itemView.findViewById(R.id.bodyTV)
        val id :TextView = itemView.findViewById(R.id.idTV)
    }

    fun updateData(newUserList: User) {
        this.userList = newUserList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item , parent ,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val userItem: UserItem = userList[position]
        holder.id.text = userItem.id.toString()
        holder.title.text = userItem.title
        holder.body.text = userItem.body
    }
}