package com.example.test.ui

import android.os.Bundle
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
class CoffeeFragment : BaseFragment<FragmentCoffeeBinding>(FragmentCoffeeBinding::inflate), CoffeeAdapter.OnItemClick {

    private val coffeeViewModel by viewModels<CoffeeViewModel>()
    private val coffeeAdapter = CoffeeAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coffeeViewModel.getAllCoffee()
        binding.rvCoffee.adapter = coffeeAdapter
        observeData()
//        coffeeAdapter.onClick={
//findNavController().navigate(CoffeeFragmentDirections.actionCoffeeFragmentToDetailFragment(it))
//        }

    }

    private fun observeData() {
        coffeeViewModel.data.observe(viewLifecycleOwner){
            coffeeAdapter.updateList(it)
        }
        coffeeViewModel.loading.observe(viewLifecycleOwner){
            if (it) binding.animationViewCoffee.visible() else binding.animationViewCoffee.gone()
        }
    }

    override fun onItemClick(id: String) {
        findNavController().navigate(CoffeeFragmentDirections.actionCoffeeFragmentToDetailFragment(id))
    }
}