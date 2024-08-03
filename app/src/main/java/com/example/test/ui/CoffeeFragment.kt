package com.example.test.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.adapter.CoffeeAdapter
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentCoffeeBinding
import com.example.test.gone
import com.example.test.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeeFragment : BaseFragment<FragmentCoffeeBinding>(FragmentCoffeeBinding::inflate) {

    private val coffeeViewModel by viewModels<CoffeeViewModel>()
    private val coffeeAdapter = CoffeeAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coffeeViewModel.getAllCoffee()
        binding.rvCoffee.adapter = coffeeAdapter

        coffeeAdapter.navigateToDetail = {
            findNavController().navigate(
                CoffeeFragmentDirections.actionCoffeeFragmentToDetailFragment(
                    it
                )
            )
        }

        observeData()


    }

    private fun observeData() {
        coffeeViewModel.data.observe(/* owner = */ viewLifecycleOwner) {
            Log.e("Coffes", it.toString())
            coffeeAdapter.updateList(it)
        }
        coffeeViewModel.loading.observe(viewLifecycleOwner) {
            if (it) binding.animationViewCoffee.visible() else binding.animationViewCoffee.gone()
        }
    }
}