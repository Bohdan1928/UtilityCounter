package com.example.utilitycounter.di

import com.example.utilitycounter.model.data.firebase.AuthRepoImp
import com.example.utilitycounter.model.repository.AuthRepo
import com.example.utilitycounter.viewModel.RegistrationViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val firebaseModule = module {
    single<AuthRepo> { AuthRepoImp(get()) }
    single { Firebase.auth }
    viewModel { RegistrationViewModel(get()) }
}
