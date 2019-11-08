package com.example.kttest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductViewModel : ViewModel() {

    val TAG : String =  ProductViewModel ::class.java.name
    val CONTENT : String = "ABCDEFGHIJKLMNOPQ";
    var mProducts: MutableLiveData<MutableList<Product>> = MutableLiveData<MutableList<Product>>()


    override fun onCleared() {
        super.onCleared()
        mProducts.value?.let{
            it.clear()
        }
    }
    fun  generateProducts(i:Int) : Product{
        val product = Product(i, i * 1.11f, CONTENT.get(i).toString())
        return product
    }

    fun  addProducts() : Boolean{

        if (mProducts.value == null) {
            mProducts.value = arrayListOf()
        }
//        var products : MutableList<Product>  = mProducts.value!!
        val products = mProducts.value!!.toMutableList()
        if (products.size >=  CONTENT.length ){
            return false
        }
        val add = products.add(generateProducts(products.size))
        mProducts.value = products
        return  add
    }

    fun deleteProduct(){
        val products = mProducts.value?.toMutableList()
        products?.let {
            if (it.size > 0)
            it.removeAt(it.size -1)
        }
        mProducts.value = products
    }

}