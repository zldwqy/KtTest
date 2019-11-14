package com.example.kttest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import com.example.kttest.databinding.FragmentBBinding
import kotlinx.android.synthetic.main.item_cart.*

class FragmentB :Fragment(){

    val TAG : String = FragmentB ::class.java.name
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bBinding = FragmentBBinding.inflate(inflater, container, false)
        subscribe(bBinding)
        return bBinding.root
    }

    private fun subscribe(bBinding: FragmentBBinding?) {
        val pos = arguments?.getInt("position")
        Log.d(TAG,"position: " + pos)

        pos?.let {
            activity?.let {
                //使用ViewModelProvider 替换 ViewModelProviders
                val viewModel = ViewModelProvider(
                    it.viewModelStore,
                    ProductViewModel.ProdecViewmodelFactory()
                ).get(ProductViewModel::class.java)

                Log.d(TAG, "ProductViewModel: " + viewModel)
                val product = viewModel?.mProducts?.value?.get(pos)
                product?.let {
                    bBinding?.txt?.text = product.toString()
                }

            }
        }


    }


    fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit)) {
        if (value1 != null && value2 != null) {
            bothNotNull(value1, value2)
        }
    }
}