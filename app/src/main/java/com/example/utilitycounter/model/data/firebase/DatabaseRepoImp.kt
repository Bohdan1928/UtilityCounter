package com.example.utilitycounter.model.data.firebase

import android.content.Context
import com.example.utilitycounter.model.AddressModel
import com.example.utilitycounter.model.repository.AuthRepo
import com.example.utilitycounter.model.repository.DatabaseRepo
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DatabaseRepoImp(authRepo: AuthRepo, database: FirebaseDatabase) :
    DatabaseRepo {

    private val databaseRef =
        database.getReference(authRepo.getUserId().toString()).child("streets")

    override fun addToDb(address: AddressModel, context: Context) {
        val streetRef = databaseRef.push()
        streetRef.setValue(address)
    }

    override fun removeFromDb() {
        TODO("Not yet implemented")
    }

    override suspend fun getAddressesFromDb(): ArrayList<AddressModel> =
        withContext(Dispatchers.IO) {
            val arrayList: List<AddressModel> =
                databaseRef.get().await().children.mapNotNull { snapshot ->
                    snapshot.getValue(AddressModel::class.java)
                }
            println("Method in Implementation")
            println(arrayList)
            return@withContext ArrayList(arrayList)
        }
}
