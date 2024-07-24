package com.example.test.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentFaceIdBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FaceIdFragment : BaseFragment<FragmentFaceIdBinding>(FragmentFaceIdBinding::inflate) {

    private val viewModel by viewModels<RegisterModelView>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ButtonYes.setOnClickListener {
            findNavController().navigate(FaceIdFragmentDirections.actionFaceIdFragmentToCoffeeSplashFragment())
        }
        faceIdVerification()

        binding.ButtonNo.setOnClickListener {
            findNavController().navigate(FaceIdFragmentDirections.actionFaceIdFragmentToHomeFragment())
        }
    }

    private fun faceIdVerification() {
        var faceId: Boolean = false
        if (faceId.equals(true)) {
            viewModel.switchFaceId(faceId)
        } else {
            context?.let { FancyToast.makeText(it, "Face Id Not Verified", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false) }
        }
    }
}