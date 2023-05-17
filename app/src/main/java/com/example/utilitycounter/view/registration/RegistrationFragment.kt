package com.example.utilitycounter.view.registration

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.utilitycounter.R
import com.example.utilitycounter.model.UserModel
import com.example.utilitycounter.view.start.StartFragment
import com.example.utilitycounter.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private val registrationViewModel: RegistrationViewModel by viewModel()

    private lateinit var btnSignUp: Button
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhoneNumber: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtRepeatPassword: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        btnSignUp = view.findViewById(R.id.btn_sign_up)
        edtName = view.findViewById(R.id.edt_name_added_address)
        edtPhoneNumber = view.findViewById(R.id.edt_apartment_add_address)
        edtEmail = view.findViewById(R.id.edt_number_of_build_add_address)
        edtPassword = view.findViewById(R.id.edt_password_sign_up)
        edtRepeatPassword = view.findViewById(R.id.edt_repeat_password_sign_up)
        val tvBack: TextView = view.findViewById(R.id.tv_back)

        tvBack.setOnClickListener {
            val startFragment = StartFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host, startFragment)
                .addToBackStack(null)
                .commit()
        }
        btnSignUp.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#426BF9"))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignUp.setOnClickListener {
            if (edtName.text.isNotEmpty()
                && edtEmail.text.isNotEmpty()
                && edtPhoneNumber.text.isNotEmpty()
                && edtPassword.text.isNotEmpty()) {
                val user = UserModel(
                    edtName.text.toString(),
                    edtEmail.text.toString(),
                    edtPhoneNumber.text.toString(),
                    edtPassword.text.toString())
                registrationViewModel.registration(
                    user, requireContext(), edtRepeatPassword.text.toString()
                ).observe(viewLifecycleOwner) { success ->
                    if (success) {
                        Toast.makeText(context, "Реєстрація успішна", Toast.LENGTH_LONG).show()
                        edtName.text = null
                        edtEmail.text = null
                        edtPhoneNumber.text = null
                        edtPassword.text = null
                        edtRepeatPassword.text = null
                    }
                }
            } else {
                Toast.makeText(context, "Не всі поля введено", Toast.LENGTH_LONG).show()
            }

        }
    }
}