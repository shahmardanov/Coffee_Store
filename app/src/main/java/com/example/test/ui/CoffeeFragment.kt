package com.example.test.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.test.R
import com.example.test.adapter.CoffeeAdapter
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentCoffeeBinding


class CoffeeFragment : BaseFragment<FragmentCoffeeBinding>(FragmentCoffeeBinding::inflate) {

    private val coffeeViewModel by viewModels<CoffeeViewModel>()
    private val coffeeAdapter = CoffeeAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeData() {
        coffeeViewModel.coffeeList.observe(viewLifecycleOwner) {
            coffeeAdapter.updateList(it)

        }
    }

}