package com.example.myrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context:Context,val post :List<Posts>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.post_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postData=post[position]
        holder.bind(postData)
    }
    override fun getItemCount()=post.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title=itemView.findViewById<TextView>(R.id.idTitle)
        val body=itemView.findViewById<TextView>(R.id.idBody)
        fun bind(post: Posts) {
            title.text=post.title
            body.text=post.body
        }

    }
}