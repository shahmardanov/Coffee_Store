package com.example.test.ui

import CardBottomFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.adapter.BasketAdapter
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentBasketBinding
import com.example.test.databinding.FragmentCardBottomBinding
import com.example.test.detail.DetailViewModel
import com.example.test.gone
import com.example.test.model.CoffeeResponseItem
import com.example.test.visible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {


    private val basketAdapter: BasketAdapter = BasketAdapter()
    private val viewModel: BasketViewModel by viewModels<BasketViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        viewModel.getBasketProducts()
        observeData()
        binding.button.setOnClickListener {
            val dialog = CardBottomFragment()
            dialog.show(requireActivity().supportFragmentManager, "AddAppForTestFragment")
        }
    }

    private fun observeData() {
        viewModel.favList.observe(viewLifecycleOwner){
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