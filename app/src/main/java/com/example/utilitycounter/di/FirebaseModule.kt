package com.example.utilitycounter.di

import com.example.utilitycounter.model.data.firebase.AuthRepoImp
import com.example.utilitycounter.model.data.firebase.DatabaseRepoImp
import com.example.utilitycounter.model.repository.AuthRepo
import com.example.utilitycounter.model.repository.DatabaseRepo
import com.example.utilitycounter.viewmodel.AddAddressesViewModel
import com.example.utilitycounter.viewmodel.RecoverPasswordViewModel
import com.example.utilitycounter.viewmodel.RegistrationViewModel
import com.example.utilitycounter.viewmodel.StartViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

val firebaseModule = module {
    single { Firebase.auth }
    single { FirebaseDatabase.getInstance() }
    single<AuthRepo> { AuthRepoImp(get()) }
    single<DatabaseRepo> { DatabaseRepoImp(get(), get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { RecoverPasswordViewModel(get()) }
    viewModel { StartViewModel(get()) }
    viewModel { AddAddressesViewModel(get()) }
}
