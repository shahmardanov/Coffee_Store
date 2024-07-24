package com.example.test.auth.register

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentRegisterBinding
import com.example.test.gone
import com.example.test.visible
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    private val viewModel: RegisterModelView by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewskip.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
        }
        observeData()
        binding.ButtonRegisterUser.setOnClickListener {
            registerUser()
        }
        binding.filledTonalButtonSignin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
    }

    private fun registerUser() {
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (password.length >= 5 && password.first().isUpperCase()) {
                viewModel.registerUser(email, password)
            } else {
                FancyToast.makeText(
                    requireContext(),
                    "Invalid Password. Password should be at least 5 characters long and start with an uppercase letter.",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    false
                ).show()
            }
        } else {
            FancyToast.makeText(
                requireContext(),
                "Invalid Email",
                FancyToast.LENGTH_SHORT,
                FancyToast.ERROR,
                false
            ).show()
        }
    }

    private fun observeData() {
        viewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it) {
                context?.let { it1 ->
                    FancyToast.makeText(
                        it1,
                        "Success",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.SUCCESS,
                        false
                    ).show()
                }
            } else {
                context?.let { it1 ->
                    FancyToast.makeText(
                        it1,
                        "Failed",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.ERROR,
                        false
                    ).show()
                }
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) binding.animationView.visible() else binding.animationView.gone()
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            context?.let { it1 ->
                FancyToast.makeText(
                    it1,
                    it,
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    false
                )
            }
        }
    }
}
