package com.example.test.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentCoffeeSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CoffeeSplashFragment : BaseFragment<FragmentCoffeeSplashBinding>(FragmentCoffeeSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToCoffee()
    }

    private fun navigateToCoffee() {
        lifecycleScope.launch{
            delay(2500)
            findNavController().navigate(CoffeeSplashFragmentDirections.actionCoffeeSplashFragmentToCoffeeLoadFragment())
        }
    }
}