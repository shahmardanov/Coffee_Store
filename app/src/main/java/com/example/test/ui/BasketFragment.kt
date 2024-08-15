package com.example.test.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.adapter.BasketAdapter
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentBasketBinding
import com.example.test.util.Helper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {


    private val basketAdapter: BasketAdapter = BasketAdapter()
    private val viewModel: BasketViewModel by viewModels<BasketViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dialog = Helper.setUpDialog(layoutInflater, requireContext()) {
            findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToSuccesFragment())
        }
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        viewModel.getBasketProducts()
        observeData()
        binding.button.setOnClickListener {
            dialog.show()

        }

        binding.buttongoToBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeData() {
        viewModel.favList.observe(viewLifecycleOwner) {
            var totalPrice = 0.0
            it.forEach { coffee ->
                if (coffee.count > 0) {
                    totalPrice += (coffee.price * coffee.count)
                }
            }
            binding.textViewtotalPrice.text = totalPrice.toString()
            basketAdapter.updateList(it)
        }
    }


    private fun setRecycler() {
        with(binding.rvBasket) {
            layoutManager = LinearLayoutManager(context)
            adapter = basketAdapter
            setHasFixedSize(true)
        }
    }
}