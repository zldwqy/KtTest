package com.example.kttest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import com.example.kttest.CommUtils.ifNotNull
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

    private fun subscribe(bBinding: FragmentBBinding) {
        val pos = arguments?.getInt("position")
        Log.d(TAG,"position: " + pos)


        ifNotNull(pos,activity){ pos ,activity ->

            val viewModel = ViewModelProvider(
                activity.viewModelStore,
                ProductViewModel.ProdecViewmodelFactory()
            ).get(ProductViewModel::class.java)

            val product = viewModel.mProducts.value?.get(pos)
            product?.let {
                bBinding.txt.text = toString()
            }
        }


    }

}