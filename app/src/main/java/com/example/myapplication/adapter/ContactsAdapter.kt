package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.EditActivity
import com.example.myapplication.R
import com.example.myapplication.model.Contacts

class ContactsAdapter(val list:ArrayList<Contacts>, var mContext: Context): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_cardview, parent, false)
        return ViewHolder(v, mContext)
    }

    override fun onBindViewHolder(holder: ContactsAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View, var mContext: Context): RecyclerView.ViewHolder(view) {
        fun bindItems(data : Contacts){
            val _textView: TextView = itemView.findViewById(R.id.tv_name)
            _textView.text = data.firstName

            itemView.setOnClickListener({
                val intent = Intent(mContext, EditActivity::class.java)
                intent.putExtra("firstName", data.firstName)
                intent.putExtra("lastName", data.lastName)
                intent.putExtra("email", data.email)
                intent.putExtra("dob", data.dod)
                mContext.startActivity(intent)
            })
        }

    }
}