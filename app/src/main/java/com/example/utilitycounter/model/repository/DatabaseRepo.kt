package com.example.utilitycounter.model.repository

import android.content.Context
import com.example.utilitycounter.model.AddressModel
import com.google.firebase.auth.FirebaseUser

interface DatabaseRepo {
    fun addToDb(address: AddressModel, context: Context)
    fun removeFromDb()
    suspend fun getAddressesFromDb(): ArrayList<AddressModel>
}