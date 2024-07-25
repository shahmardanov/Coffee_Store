package com.example.test.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentOnBoard3Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoard3Fragment : BaseFragment<FragmentOnBoard3Binding>(FragmentOnBoard3Binding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ButtonNext.setOnClickListener {
            findNavController().navigate(OnBoard3FragmentDirections.actionOnBoard3FragmentToHomeFragment())
        }
    }
}