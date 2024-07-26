package com.example.test.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentSuccesBinding


class SuccesFragment : BaseFragment<FragmentSuccesBinding>(FragmentSuccesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener {
            findNavController().navigate(SuccesFragmentDirections.actionSuccesFragmentToHomeMainFragment())
        }
    }

}