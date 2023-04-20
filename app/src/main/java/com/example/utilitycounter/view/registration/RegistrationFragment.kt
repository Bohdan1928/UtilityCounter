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
import androidx.fragment.app.viewModels
import com.example.utilitycounter.R
import com.example.utilitycounter.model.data.firebase.UserModel
import com.example.utilitycounter.view.start.StartFragment
import com.example.utilitycounter.viewModel.RegistrationViewModel
import com.example.utilitycounter.viewModel.ViewModelFactory
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class RegistrationFragment : Fragment() {

    private val singUpViewModel by viewModels<RegistrationViewModel> {
        ViewModelFactory(get())
    }

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
        edtName = view.findViewById(R.id.edt_name_sign_up)
        edtPhoneNumber = view.findViewById(R.id.edt_phone_number_sign_up)
        edtEmail = view.findViewById(R.id.edt_email_sign_up)
        edtPassword = view.findViewById(R.id.edt_password_sign_up)
        edtRepeatPassword = view.findViewById(R.id.edt_repeat_password_sign_up)
        val btnBack: TextView = view.findViewById(R.id.tv_back)

        btnBack.setOnClickListener {
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
                && edtPassword.text.isNotEmpty()
            ) {
                if (edtRepeatPassword.text.toString() == edtPassword.text.toString()) {

                    val user = UserModel(
                        edtName.text.toString(),
                        edtEmail.text.toString(),
                        edtPhoneNumber.text.toString(),
                        edtPassword.text.toString()
                    )
                    singUpViewModel.registration(user).observe(viewLifecycleOwner) { success ->
                        if (success) {
                            Toast.makeText(context, "Реєстрація успішна", Toast.LENGTH_LONG).show()
                            edtName.text = null
                            edtEmail.text = null
                            edtPhoneNumber.text = null
                            edtPassword.text = null
                            edtRepeatPassword.text = null
                        } else {
                            Toast.makeText(context, "Помилка реєстрації", Toast.LENGTH_LONG).show()
                            edtName.text = null
                            edtEmail.text = null
                            edtPhoneNumber.text = null
                            edtPassword.text = null
                            edtRepeatPassword.text = null
                        }
                    }
                } else {
                    Toast.makeText(context, "Паролі відрізняються", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Не всі поля введено", Toast.LENGTH_LONG).show()
            }

        }
    }
}