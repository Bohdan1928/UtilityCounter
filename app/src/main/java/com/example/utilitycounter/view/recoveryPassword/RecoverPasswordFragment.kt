package com.example.utilitycounter.view.recoveryPassword

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
import com.example.utilitycounter.view.start.StartFragment
import com.example.utilitycounter.viewModel.RecoverPasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecoverPasswordFragment : Fragment() {

    private val recoverPasswordViewModel: RecoverPasswordViewModel by viewModel()

    private lateinit var edtEmailRecoverPassword: EditText
    private lateinit var btnSendRequest: Button
    private lateinit var tvBack: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recover_password, container, false)
        edtEmailRecoverPassword = view.findViewById(R.id.edt_email_recover_password)
        btnSendRequest = view.findViewById(R.id.btn_send_request)
        tvBack = view.findViewById(R.id.tv_back_recover_password)

        tvBack.setOnClickListener {
            val startFragment = StartFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host, startFragment)
                .addToBackStack(null)
                .commit()
        }
        btnSendRequest.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#426BF9"))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSendRequest.setOnClickListener {
            if (edtEmailRecoverPassword.text.isNotEmpty()) {
                recoverPasswordViewModel
                    .sendMailForChangePassword(
                        edtEmailRecoverPassword.text.toString(),
                        requireContext()
                    )
                    .observe(viewLifecycleOwner) {
                        if (it) {
                            edtEmailRecoverPassword.text = null
                        }
                    }
            }
        }
    }

}