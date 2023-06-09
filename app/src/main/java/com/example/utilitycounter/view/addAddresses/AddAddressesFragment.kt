package com.example.utilitycounter.view.addAddresses

import android.content.res.ColorStateList
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitycounter.R
import com.example.utilitycounter.viewmodel.AddAddressesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddAddressesFragment : Fragment() {

    private val addAddressesViewModel: AddAddressesViewModel by viewModel()

    private lateinit var edtNameAddAddress: EditText
    private lateinit var edtNumberOfBuild: EditText
    private lateinit var edtNumberOfApartment: EditText
    private lateinit var radioButton: RadioButton
    private lateinit var btnAddAddress: Button
    private lateinit var btnRemoveAddress: Button
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_addresses, container, false)

        recyclerView = view.findViewById(R.id.rv_addresses)
        edtNameAddAddress = view.findViewById(R.id.edt_name_added_address)
        edtNumberOfBuild = view.findViewById(R.id.edt_number_of_build_add_address)
        edtNumberOfApartment = view.findViewById(R.id.edt_apartment_add_address)
        radioButton = view.findViewById(R.id.radio_private_house)
        btnAddAddress = view.findViewById(R.id.btn_add_address)
        btnRemoveAddress = view.findViewById(R.id.btn_remove_address)
        recyclerView.layoutManager = LinearLayoutManager(context)

        btnAddAddress.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#426BF9"))
        btnRemoveAddress.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF6951"))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (addAddressesViewModel.adapter.getInfoList()) {
            recyclerView.visibility = View.VISIBLE
        }
        recyclerView.adapter = addAddressesViewModel.adapter
        addAddressesViewModel.getAddressDataFromDb()

        radioButton.setOnClickListener {
            edtNumberOfApartment.text = null
            edtNumberOfApartment.isEnabled = false
        }
        btnAddAddress.setOnClickListener {
            if (edtNameAddAddress.text.isNotEmpty()
                && edtNumberOfBuild.text.isNotEmpty()
            ) {
                val address = addAddressesViewModel
                    .getAddress(
                        edtNameAddAddress.text.toString(),
                        edtNumberOfBuild.text.toString(),
                        edtNumberOfApartment.text.toString(),
                        radioButton.isChecked.toString()
                    )
                addAddressesViewModel.addToDb(address, requireContext())

                edtNameAddAddress.text = null
                edtNumberOfBuild.text = null
                edtNumberOfApartment.text = null
                radioButton.isChecked = false

                recyclerView.visibility = View.VISIBLE
                addAddressesViewModel.getAddressDataFromDb()
            }
        }
    }
}
