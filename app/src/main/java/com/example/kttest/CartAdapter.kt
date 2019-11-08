package com.example.kttest

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kttest.databinding.ItemCartBinding

class CartAdapter : ListAdapter<Product,CartAdapter.Holder>(CartDiff()){
    val TAG : String = "CartAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        Log.d(TAG,"onCreateViewHolder")
        var databinding : ItemCartBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_cart,parent,false)
        return Holder(databinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Log.d(TAG,"onBindViewHolder: " + position)
        getItem(position)?.let {
            with(holder){
                itemCartBinding.name.text = it.name
                itemCartBinding.price.text = it.price.toString()
            }
        }
    }


    class Holder :RecyclerView.ViewHolder{

       var itemCartBinding : ItemCartBinding

        constructor(itemCartBinding: ItemCartBinding?) : super(itemCartBinding?.root!!) {
            this.itemCartBinding = itemCartBinding

        }
    }

    class CartDiff : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id && oldItem.name.equals(newItem.name)
                    && oldItem.price.equals(newItem.price)
        }

    }
}