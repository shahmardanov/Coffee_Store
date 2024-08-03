package com.example.test.homemain

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
import com.example.test.databinding.FragmentHomeMainBinding
import com.example.test.ui.CoffeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMainFragment : BaseFragment<FragmentHomeMainBinding>(FragmentHomeMainBinding::inflate) {

    private val viewModel by viewModels<CoffeeViewModel>()
   // private val coffeeAdapter = CoffeeAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        coffeeAdapter.onClickItem={
//            viewModel.addProduct(it)
//        }


        binding.imageViewbasket.setOnClickListener {
            findNavController().navigate(HomeMainFragmentDirections.actionHomeMainFragmentToBasketFragment())
        }

        binding.imageViewcoffee.setOnClickListener {
            findNavController().navigate(HomeMainFragmentDirections.actionHomeMainFragmentToCoffeeFragment())
        }
    }


}