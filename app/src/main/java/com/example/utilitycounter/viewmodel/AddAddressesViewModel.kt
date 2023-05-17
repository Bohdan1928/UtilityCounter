package com.example.utilitycounter.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilitycounter.model.AddressModel
import com.example.utilitycounter.model.repository.DatabaseRepo
import com.example.utilitycounter.view.addAddresses.AdapterAddresses
import kotlinx.coroutines.*

class AddAddressesViewModel(private val databaseRepo: DatabaseRepo) : ViewModel() {

    val adapter = AdapterAddresses()
    fun getAddress(
        street: String, numberOfBuild: String, numberOfApartment: String?, privateHouse: String
    ): AddressModel {
        return AddressModel(street, numberOfBuild, numberOfApartment, privateHouse)
    }

    fun addToDb(address: AddressModel, context: Context) {
        databaseRepo.addToDb(address, context)
    }

    fun getAddressDataFromDb() {
        viewModelScope.launch {
            async {
                adapter.changeList(databaseRepo.getAddressesFromDb())
            }
        }
    }
}
