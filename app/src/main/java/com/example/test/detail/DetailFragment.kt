package com.example.test.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.test.R
import com.example.test.adapter.CoffeeAdapter
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentDetailBinding
import com.example.test.gone
import com.example.test.model.CoffeeResponseItem
import com.example.test.ui.CoffeeViewModel
import com.example.test.ui.DetailResponseState
import com.example.test.visible
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()
    private val coffeeViewModel: CoffeeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewBack.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToCoffeeFragment())
        }
        viewModel.getCoffeeById(args.id)
        setupObservers()

        binding.buttonaddToBasket.setOnClickListener {
            coffeeViewModel.addProduct(viewModel.cofeeDetail.value!!)
        }


    }

    private fun setupObservers() {
        viewModel.cofeeDetail.observe(viewLifecycleOwner) {
            binding.product = it
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.loadingDetailAnim.visible()
            } else {
                binding.loadingDetailAnim.gone()
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            FancyToast.makeText(requireContext(), it, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false)
                .show()
        }
    }
}