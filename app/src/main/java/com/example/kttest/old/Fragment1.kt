package com.example.kttest.old

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kttest.databinding.Fragment1Binding

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = Fragment1Binding.inflate(inflater, container, false)
        initListener(binding)
        return binding.root
    }

    private fun initListener(binding: Fragment1Binding?) {

        if (binding != null) {
            binding.listview.adapter = UserListAdapter()
        }
    }

}