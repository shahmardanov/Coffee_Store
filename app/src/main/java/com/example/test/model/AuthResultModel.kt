package com.example.test.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthResultModel(
    val message: String? = null,
    val isSuccess: Boolean,
    val userName: String,
): Parcelable
