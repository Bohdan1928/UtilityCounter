package com.example.utilitycounter.view.fragments.start

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
import com.example.utilitycounter.R
import com.example.utilitycounter.view.fragments.addAddresses.AddAddressesFragment
import com.example.utilitycounter.view.fragments.recoveryPassword.RecoverPasswordFragment
import com.example.utilitycounter.view.fragments.registration.RegistrationFragment
import com.example.utilitycounter.viewmodels.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

    private val startViewModel: StartViewModel by viewModel()

    private lateinit var btnSignIn: Button
    private lateinit var edtEmailStart: EditText
    private lateinit var edtPasswordStart: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        btnSignIn = view.findViewById(R.id.btn_sign_in)
        val btnRecoverPassword: Button = view.findViewById(R.id.btn_recover_password)
        val tvSignUp: TextView = view.findViewById(R.id.tv_sing_up)
        edtEmailStart = view.findViewById(R.id.edt_email_sign_in)
        edtPasswordStart = view.findViewById(R.id.edt_password_sign_in)

        tvSignUp.setOnClickListener {
            val registrationFragment = RegistrationFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host, registrationFragment)
                .addToBackStack(null)
                .commit()
        }

        btnRecoverPassword.setOnClickListener {
            val recoverPasswordFragment = RecoverPasswordFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host, recoverPasswordFragment)
                .addToBackStack(null)
                .commit()
        }

        btnSignIn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#426BF9"))
        btnRecoverPassword.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1CCD9D"))

        btnSignIn.setOnClickListener {
            if (edtEmailStart.text.isNotEmpty()
                && edtPasswordStart.text.isNotEmpty()
            ) {
                startViewModel.signIn(
                    edtEmailStart.text.toString(),
                    edtPasswordStart.text.toString(),
                    requireContext()
                ).observe(viewLifecycleOwner) {
                    if (it) {
                        val addAddressesFragment = AddAddressesFragment()
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.nav_host, addAddressesFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                }

            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}