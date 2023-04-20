package com.example.utilitycounter.view.start

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.utilitycounter.R
import com.example.utilitycounter.view.registration.RegistrationFragment

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        val btnSignIn: Button = view.findViewById(R.id.btn_sign_in)
        val btnRecoverPassword: Button = view.findViewById(R.id.btn_recover_password)
        val tvSignUp: TextView = view.findViewById(R.id.tv_sing_up)

        tvSignUp.setOnClickListener {
            val registrationFragment = RegistrationFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host, registrationFragment)
                .addToBackStack(null)
                .commit()
        }

        btnSignIn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#426BF9"))
        btnRecoverPassword.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1CCD9D"))

        return view
    }

}