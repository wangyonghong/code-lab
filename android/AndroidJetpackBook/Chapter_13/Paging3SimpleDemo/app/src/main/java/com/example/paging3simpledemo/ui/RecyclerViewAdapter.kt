package com.example.paging3simpledemo.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3simpledemo.R
import com.example.paging3simpledemo.common.entity.ResponseEntity
import java.util.*

class RecyclerViewAdapter :
    PagingDataAdapter<ResponseEntity.SingleUserData, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<ResponseEntity.SingleUserData>() {

        override fun areItemsTheSame(
            oldItem: ResponseEntity.SingleUserData,
            newItem: ResponseEntity.SingleUserData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: ResponseEntity.SingleUserData,
            newItem: ResponseEntity.SingleUserData
        ): Boolean {
            return oldItem.avatar.equals(newItem.avatar) &&
                    oldItem.email.equals(newItem.email) &&
                    oldItem.first_name.equals(newItem.first_name) &&
                    oldItem.last_name.equals(newItem.last_name)
        }
    }) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).nameTv.text =
            (Objects.requireNonNull<Any?>(getItem(position)) as ResponseEntity.SingleUserData).first_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.item_name)

    }

}