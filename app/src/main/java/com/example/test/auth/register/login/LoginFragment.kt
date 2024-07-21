package com.example.test.auth.register.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.auth.register.RegisterModelView
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentLoginBinding
import com.example.test.gone
import com.example.test.visible
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val modelView by viewModels<RegisterModelView>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let { FancyToast.makeText(it, "Welcome to Login", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show() }
        super.onViewCreated(view, savedInstanceState)
        binding.filledTonalButtoncreateNewAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

    private fun loginUser() {
        val email = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            modelView.loginUser(email, password)
        } else {
            context?.let {
                FancyToast.makeText(
                    it,
                    "Please fill all the fields",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.WARNING,
                    false
                ).show()
            }
        }
    }

    private fun observeData() {
        modelView.isSuccess.observe(viewLifecycleOwner) {
            if (it) {
                if (binding.materialSwich.isChecked) setUserAuth()
                context?.let { it1 ->
                    FancyToast.makeText(
                        it1,
                        "Login Successful",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.SUCCESS,
                        false
                    ).show()
                }
            } else {
                context?.let { it2 ->
                    FancyToast.makeText(
                        it2,
                        "Login Failed",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.ERROR,
                        false
                    ).show()
                }
            }
        }

        modelView.loading.observe(viewLifecycleOwner) {
            if (it) binding.animationView.visible() else binding.animationView.gone()
        }
    }



    private fun setUserAuth() {
        val sp = requireActivity().getSharedPreferences("product_local", Context.MODE_PRIVATE)
        sp.edit().putBoolean("isAuth", true).apply()
    }
}