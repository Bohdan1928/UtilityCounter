package com.example.utilitycounter.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilitycounter.R
import com.example.utilitycounter.model.data.firebase.UserModel
import com.example.utilitycounter.model.repository.AuthRepo
import com.example.utilitycounter.view.start.StartFragment
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

    fun logOut(){
        viewModelScope.launch {
            authRepo.logout()
        }
    }
}