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
import com.example.test.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment :
    BaseFragment<FragmentOnBoardingBinding>(FragmentOnBoardingBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ButtonNext.setOnClickListener {
            navigateToOnBoarding2()
        }
    }

    private fun navigateToOnBoarding2() {
        lifecycleScope.launch {
            findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToOnBoarding2Fragment())
        }
    }
}