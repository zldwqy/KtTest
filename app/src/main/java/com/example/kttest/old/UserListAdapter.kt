package com.example.kttest.old

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kttest.R
import com.example.kttest.databinding.ItemUserBinding

class UserListAdapter : ListAdapter<User, UserListAdapter.Holder>(
    UserDiff()
){
    val TAG : String = "UserListAdapter";
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        return Holder(
            DataBindingUtil.inflate(
                LayoutInflater.from(
                    parent.context
                ), R.layout.item_user, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        getItem(position).let { user ->
            with(holder){
                itemView.tag = user
                holder.itemView.setOnClickListener(createOnClickListener(position))
            }
        }
    }
    fun createOnClickListener (position: Int) : View.OnClickListener{
        return View.OnClickListener {
            Log.d(TAG,"createOnClickListener: " + position);
        }
    }

    class Holder(binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){



    }

    class UserDiff : DiffUtil.ItemCallback<User>(){
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.age == newItem.age && oldItem.name.equals(newItem.name)
        }

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.equals(newItem)
        }

    }
}