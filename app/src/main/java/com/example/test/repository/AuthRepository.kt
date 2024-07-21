package com.example.test.repository

import com.example.test.model.AuthResultModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {


    suspend fun registerUser(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()

    suspend fun loginUser(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
}