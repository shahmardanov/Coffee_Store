package com.example.test.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
       navigateToHome()

   }

    private fun navigateToHome() {
        lifecycleScope.launch {
            delay(1500)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToRegisterFragment())
        }
    }
}