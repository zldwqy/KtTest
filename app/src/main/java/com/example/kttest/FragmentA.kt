package com.example.kttest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kttest.databinding.FragmentABinding

class FragmentA : Fragment() {
    val TAG : String = FragmentA ::class.java.name

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val aBinding = FragmentABinding.inflate(inflater, container, false)
        val cartAdapter = CartAdapter()
        aBinding.recyclerview.adapter = cartAdapter
        aBinding.recyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        subScribe(cartAdapter,aBinding)
        return aBinding.root
    }

    private fun subScribe(
        adapter: CartAdapter,
        aBinding: FragmentABinding
    ) {
        val productViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
            .create(ProductViewModel::class.java)
        productViewModel.mProducts.observe(viewLifecycleOwner, Observer {

            Log.d(TAG, "products has changed" + it)
            if (!it.isNullOrEmpty())
                adapter.submitList(it)
        })
        aBinding.add.setOnClickListener {
            val addProducts = productViewModel.addProducts()
            Log.d(TAG,"addProducts; "+addProducts)
        }
        aBinding.delete.setOnClickListener {
            productViewModel.deleteProduct()
        }
    }

}