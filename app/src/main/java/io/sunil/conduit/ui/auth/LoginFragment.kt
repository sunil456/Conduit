package io.sunil.conduit.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import io.sunil.api.models.entities.User
import io.sunil.conduit.databinding.FragmentLoginSignupBinding

class LoginFragment : Fragment() {

    private var _fragmentLoginBinding : FragmentLoginSignupBinding? = null

     val authViewModel: AuthViewModel by activityViewModels()

    private val loginBinding get() = _fragmentLoginBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _fragmentLoginBinding = FragmentLoginSignupBinding.inflate(inflater, container, false)
//        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBinding.userNmaeEditText.isVisible = false

        loginBinding.apply {
            submitButton.setOnClickListener {
                authViewModel?.login(emailEditText.text.toString(), passwordEditText.text.toString())
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentLoginBinding = null
    }
}