package com.example.utilitycounter.model.data.firebase

import android.content.Context
import android.widget.Toast
import com.example.utilitycounter.model.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepoImp(private val firebaseAuth: FirebaseAuth) : AuthRepo {

    override suspend fun register(
        email: String,
        password: String,
        context: Context,
        passwordRepeat: String,

        ): Boolean {
        var flag = false
        if (checkFormatEmail(email, context)) {
            coroutineScope {
                if (checkValidEmail(email, context)) {
                    if (checkFormatPassword(password, context)) {
                        if (checkValidPassword(password, passwordRepeat, context)) {

                            firebaseAuth.createUserWithEmailAndPassword(email, password)
                            flag = true
                        }
                    }
                }
            }

        }
        return flag
    }

    override suspend fun login(email: String, password: String, context: Context): Boolean {
        var flag = false
        withContext(Dispatchers.IO) {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            try {
                if (result.user != null) {
                    flag = true
                }
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Введено невірний email або пароль",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        return flag
    }


    override fun getUserId(): String? {
        return firebaseAuth.uid
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }


    override suspend fun changePassword(email: String, context: Context): Boolean {
        return sendMailForResetPassword(email, context)
    }


    private suspend fun checkValidEmail(email: String, context: Context): Boolean =
        withContext(Dispatchers.IO) {
            val flag: Boolean
            val result = firebaseAuth.fetchSignInMethodsForEmail(email).await()
            flag = if (result.signInMethods.isNullOrEmpty()) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context, "Введений email вже використовується", Toast.LENGTH_SHORT
                    ).show()
                }
                false
            } else {
                true
            }
            flag
        }

    private fun checkFormatEmail(email: String, context: Context): Boolean {
        val flag: Boolean
        val regexEmail = "^[\\w.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,5}\$".toRegex()
        flag = if (email.matches(regexEmail)) {
            true
        } else {
            Toast.makeText(context, "Некоректний формат email", Toast.LENGTH_SHORT).show()
            false
        }
        return flag
    }

    private fun checkFormatPassword(password: String, context: Context): Boolean {
        val flag: Boolean
        val passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&+=])(?=\\S+$).{8,}$".toRegex()
        flag = if (password.matches(passwordPattern)) {
            true
        } else {
            Toast.makeText(context, "Пароль не надійний", Toast.LENGTH_SHORT).show()
            false
        }
        return flag
    }

    private fun checkValidPassword(
        password: String, passwordRepeat: String, context: Context
    ): Boolean {
        val flag = if (password == passwordRepeat) {
            true
        } else {
            Toast.makeText(context, "Паролі відрізняються", Toast.LENGTH_LONG).show()
            false
        }
        return flag
    }

    private suspend fun sendMailForResetPassword(email: String, context: Context): Boolean =
        withContext(Dispatchers.IO) {
            if (checkFormatEmail(email, context)) {
                firebaseAuth.sendPasswordResetEmail(email).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context, "Лист надіслано на електронну пошту", Toast.LENGTH_SHORT
                    ).show()
                }
                return@withContext true
            } else {
                return@withContext false
            }
        }
}
