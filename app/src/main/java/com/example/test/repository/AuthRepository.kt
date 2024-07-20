package com.example.test.repository

import com.example.test.model.AuthResultModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {


    suspend fun registerUser(
        userEmail: String,
        userName: String,
        userPassword: String
    ): AuthResultModel {
        return try {
            val userCredential =
                firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword).await()
            val user = userCredential.user
            if (user != null) {
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(userName)
                    .build()
                user.updateProfile(profileUpdates).await()
            }
            AuthResultModel("Registration successful", true, userName)
        } catch (e: Exception) {
            AuthResultModel("Registration failed: ${e.message}", false, userName)
        }

    }

    suspend fun loginUser(userName: String, userPassword: String): AuthResultModel {
        return try {
            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).await()
            AuthResultModel("Login successful", true, userName)
        } catch (e: Exception) {
            AuthResultModel(e.localizedMessage?.toString(), false, userName)

        }
    }
}