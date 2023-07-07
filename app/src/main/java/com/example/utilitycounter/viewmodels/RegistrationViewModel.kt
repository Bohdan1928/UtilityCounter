package com.example.utilitycounter.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilitycounter.model.UserModel
import com.example.utilitycounter.model.repository.AuthRepo
import kotlinx.coroutines.launch

class RegistrationViewModel(private val authRepo: AuthRepo) : ViewModel() {

    private val _singUpSuccess = MutableLiveData<Boolean>()
    val singUpSuccess: LiveData<Boolean>
        get() = _singUpSuccess

    fun registration(user: UserModel, context: Context, passwordRepeat: String): LiveData<Boolean> {
        viewModelScope.launch {
            val isSuccess = authRepo.register(user.email, user.password, context, passwordRepeat)
            _singUpSuccess.value = isSuccess
        }
        return _singUpSuccess
    }

}