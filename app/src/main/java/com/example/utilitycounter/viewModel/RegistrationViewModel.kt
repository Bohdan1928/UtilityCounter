package com.example.utilitycounter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.utilitycounter.model.data.firebase.UserModel
import com.google.firebase.auth.FirebaseAuth

class RegistrationViewModel(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    private val _singUpSuccess = MutableLiveData<Boolean>()
    val singUpSuccess: LiveData<Boolean>
        get() = _singUpSuccess

    fun registration(user: UserModel): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _singUpSuccess.value = true
                    result.value = true
                } else {
                    _singUpSuccess.value = false
                    result.value = false
                }
            }
        return result
    }

}