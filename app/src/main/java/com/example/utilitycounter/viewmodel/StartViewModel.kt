package com.example.utilitycounter.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilitycounter.model.repository.AuthRepo
import kotlinx.coroutines.launch

class StartViewModel(private val authRepo: AuthRepo) : ViewModel() {

    private val _signInLiveData = MutableLiveData<Boolean>()
    val signInLiveData: LiveData<Boolean>
        get() = _signInLiveData

    fun signIn(email: String, password: String, context: Context): LiveData<Boolean> {
        viewModelScope.launch {
            val isSuccess = authRepo.login(email, password, context)
            _signInLiveData.value = isSuccess
        }
        return _signInLiveData
    }
}