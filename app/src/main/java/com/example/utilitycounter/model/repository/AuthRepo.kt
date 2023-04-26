package com.example.utilitycounter.model.repository

import android.content.Context

interface AuthRepo {
    suspend fun register(email: String, password: String, context: Context, passwordRepeat: String): Boolean
    suspend fun login(email: String, password: String): Boolean
    suspend fun logout()
    suspend fun changePassword(email: String, context: Context): Boolean
}