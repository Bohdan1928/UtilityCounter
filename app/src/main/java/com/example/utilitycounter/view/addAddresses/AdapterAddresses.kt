package com.example.utilitycounter.view.addAddresses

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.utilitycounter.R
import com.example.utilitycounter.model.AddressModel

class AdapterAddresses :
    Adapter<AdapterAddresses.AddressesViewHolder>() {
    private var emptyList = ArrayList<AddressModel>()

    class AddressesViewHolder(itemView: View) : ViewHolder(itemView) {
        val nameOfAddress: TextView = itemView.findViewById(R.id.item_addresses_name)
        val street: TextView = itemView.findViewById(R.id.item_number_of_build_and_apartment)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeList(list: ArrayList<AddressModel>): ArrayList<AddressModel> {
        emptyList.clear()
        emptyList += list
        notifyDataSetChanged()
        return list
    }

    fun getInfoList(): Boolean {
        return emptyList.isEmpty()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressesViewHolder {
        val holder =
            LayoutInflater.from(parent.context).inflate(R.layout.address_item, parent, false)
        return AddressesViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return emptyList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AddressesViewHolder, position: Int) {
        val nameOfAddress = emptyList[position].street
        val numberOfBuild = "Будинок ${emptyList[position].numberOfBuild}, "
        val numberOfApartment = "кв. ${emptyList[position].numberOfApartment}"
        val privateHouse = emptyList[position].privateHouse
        val numberOfBuildAndApartment = numberOfBuild + numberOfApartment
        holder.nameOfAddress.text = nameOfAddress
        if (privateHouse != "true") {
            holder.street.text = numberOfBuildAndApartment
        } else {
            holder.street.text = "$numberOfBuild приватний будинок"
        }
    }
}