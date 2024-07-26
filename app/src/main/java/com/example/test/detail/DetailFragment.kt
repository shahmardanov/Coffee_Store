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
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentDetailBinding
import com.example.test.gone
import com.example.test.model.CoffeeResponseItem
import com.example.test.ui.DetailResponseState
import com.example.test.visible
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCoffeeById(args.id)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.cofeeDetail.observe(viewLifecycleOwner) {

            when (it) {
                DetailResponseState.Loading -> {
                    binding.loadingDetailAnim.visible()
                }
                is DetailResponseState.Error -> {
                    binding.loadingDetailAnim.gone()
                    FancyToast.makeText(
                        requireContext(),
                        it.message.toString(),
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        false
                    ).show()
                }
                is DetailResponseState.Success -> {
                    binding.loadingDetailAnim.gone()
                    binding.product = it.result.firstOrNull()
                    Log.d("detail", "setupObservers: ${it.result}")


                }
            }
        }
    }
}