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
//    private val viewModel: DetailViewModel by viewModels<DetailViewModel>()
    private var list = arrayListOf<CoffeeResponseItem>()

    fun createFinished() {
        setRecycler()
//        viewModel.getBasket()
        binding.button.setOnClickListener {
            val dialog = BottomSheetDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "AddAppForTestFragment")
            if (list.isNotEmpty()) {

//                findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToCardBottomFragment3())
            } else {
                Toast.makeText(context, "Sepetiniz Boş", Toast.LENGTH_LONG).show()
            }
        }
        binding.buttonBasketNavigation.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
//        viewModel.getBasket()
        binding.button.setOnClickListener {
            val dialog = CardBottomFragment()
            dialog.show(requireActivity().supportFragmentManager, "AddAppForTestFragment")
        }


    }

    fun observeEvents() {
//        with(viewModel) {
//            with(binding) {
//                basketList.observe(viewLifecycleOwner) {
//                    it?.let { it1 -> list.addAll(it1) }
//                    basketAdapter.updateList(list)
//                }
//
//                totalPriceBasket.observe(viewLifecycleOwner){
//                    it?.let {
//                        textViewtotalPrice.text="$it $"
//                    }
//                }
//
//                loading.observe(viewLifecycleOwner){
//                    if (it){
//                        animationView.visible()
//                        rvBasket.visibility=View.INVISIBLE
//                    }else{
//                        animationView.gone()
//                        rvBasket.visibility=View.VISIBLE
//                    }
//                }
//            }
//
//        }
    }

    private fun setRecycler() {
        with(binding.rvBasket) {
            layoutManager = LinearLayoutManager(context)
            adapter = basketAdapter
            setHasFixedSize(true)
        }
    }
}