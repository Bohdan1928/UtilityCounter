package com.example.utilitycounter.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.utilitycounter.R
import com.example.utilitycounter.model.UtilityModel


class AdapterUtilities : Adapter<AdapterUtilities.UtilitiesViewHolder>() {
    private var arrayList = ArrayList<UtilityModel>()

    class UtilitiesViewHolder(itemView: View) : ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilitiesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.utility_item, parent, false)
        return UtilitiesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UtilitiesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}