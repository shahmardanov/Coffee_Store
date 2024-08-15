package com.example.test.util

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.test.databinding.PaymentDialogBinding

class Helper {
    companion object {
        fun setUpDialog(
            layoutInflater: LayoutInflater,
            requireContext: Context,
            navigate: () -> Unit
        ): AlertDialog {
            val dialogBinding = PaymentDialogBinding.inflate(layoutInflater)
            val dialog = AlertDialog.Builder(requireContext).create();

            dialog.setView(dialogBinding.root)

            dialogBinding.bottomButton.setOnClickListener {
                dialog.dismiss()
                navigate()
            }


            return dialog
        }
    }
}