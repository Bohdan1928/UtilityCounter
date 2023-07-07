package com.example.utilitycounter.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilitycounter.model.AddressModel
import com.example.utilitycounter.model.repository.AuthRepo
import com.example.utilitycounter.model.repository.DatabaseRepo
import com.example.utilitycounter.view.adapters.AdapterAddresses
import kotlinx.coroutines.*

class AddAddressesViewModel(
    private val databaseRepo: DatabaseRepo,
    private val authRepo: AuthRepo
) : ViewModel() {

    val adapter = AdapterAddresses()
    lateinit var address: AddressModel

    private val _isDataAvailable = MutableLiveData(false)
    private val _isAddressDuplicate = MutableLiveData(true)
    val isDataAvailable: LiveData<Boolean>
        get() = _isDataAvailable

    val isAddressDuplicate: LiveData<Boolean>
        get() = _isAddressDuplicate

    fun createAddress(
        street: String, numberOfBuild: String, numberOfApartment: String?, privateHouse: String
    ): AddressModel {
        address = AddressModel(street, numberOfBuild, numberOfApartment, privateHouse)
        return address
    }

    fun addToDb(address: AddressModel, context: Context) {
        databaseRepo.addToDb(address, context)
    }

    fun getAddressDataFromDb() {
        viewModelScope.launch {
            async {
                val list = adapter.changeList(databaseRepo.getAddressesFromDb())
                _isDataAvailable.value = list.isNotEmpty()
            }
        }
    }

    fun isAddressDuplicate(address: AddressModel) {
        viewModelScope.launch {
            async {
                _isAddressDuplicate.value = databaseRepo.isAddressDuplicate(address)
            }
        }
    }

    fun logOut(){
        viewModelScope.launch {
            authRepo.logout()
        }
    }

}
