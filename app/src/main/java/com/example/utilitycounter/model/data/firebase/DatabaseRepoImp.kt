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

    private val userRoot = database.getReference("Users")
    private val databaseRef =
        userRoot.child(authRepo.getUserId().toString()).child("streets")

    override fun addToDb(address: AddressModel, context: Context) {
        val streetRef = databaseRef.push()
        streetRef.setValue(address)
    }

    override fun removeFromDb() {

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

    override suspend fun isAddressDuplicate(address: AddressModel): Boolean = withContext(Dispatchers.IO) {
        val addressHash = address.hashCode()
        val addresses = databaseRef.get().await().children
        for (snap in addresses) {
            val existingAddress = snap.getValue(AddressModel::class.java)
            if (existingAddress != null && existingAddress.hashCode() == addressHash) {
                return@withContext true
            }
        }
        return@withContext false
    }
}


