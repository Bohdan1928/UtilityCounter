package com.example.utilitycounter.viewModel

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilitycounter.model.data.firebase.UserModel
import com.example.utilitycounter.model.repository.AuthRepo
import kotlinx.coroutines.launch

class RecoverPasswordViewModel(private val authRepo: AuthRepo): ViewModel() {
    private val _recoverPasswordLiveData = MutableLiveData<Boolean>()
    val recoverPasswordLiveData: LiveData<Boolean>
        get() = _recoverPasswordLiveData

    fun sendMailForChangePassword(email: String, context: Context): LiveData<Boolean>{
        viewModelScope.launch {
            val isSuccess = authRepo.changePassword(email, context)
            _recoverPasswordLiveData.value = isSuccess
        }
        return _recoverPasswordLiveData
    }
}