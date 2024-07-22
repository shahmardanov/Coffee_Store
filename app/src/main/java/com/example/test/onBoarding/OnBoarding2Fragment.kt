package com.example.test.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentOnBoarding2Binding
import kotlinx.coroutines.launch


class OnBoarding2Fragment :
    BaseFragment<FragmentOnBoarding2Binding>(FragmentOnBoarding2Binding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ButtonNext.setOnClickListener {
            navigateToOnBoarding3()
        }
    }

    private fun navigateToOnBoarding3() {
        lifecycleScope.launch {
            findNavController().navigate(OnBoarding2FragmentDirections.actionOnBoarding2FragmentToOnBoard3Fragment())
        }
    }
}