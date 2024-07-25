package com.example.test.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentDetailBinding
import com.example.test.model.CoffeeResponseItem


class DetailFragment :
    BaseFragment<DetailViewModel, FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override val viewModel: DetailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.category?.let {
            coffeeItem = it
        }
    }

    override fun createFinished() {
        with(binding) {
            product = args.category
            binding.executePendingBindings()
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonaddToBasket.setOnClickListener {
                viewModel.addToBasket(product!!,1)
findNavController().navigate(DetailFragmentDirections.())
            }
        }
    }

    override fun observeEvents() {

    }

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var coffeeItem: CoffeeResponseItem


}