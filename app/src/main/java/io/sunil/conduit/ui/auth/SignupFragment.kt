package io.sunil.conduit.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.sunil.conduit.databinding.FragmentLoginSignupBinding
import io.sunil.conduit.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {


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

        loginBinding.userNmaeEditText.isVisible = true

        loginBinding.apply {
            submitButton.setOnClickListener {
                authViewModel.signup(userNmaeEditText.text.toString(),emailEditText.text.toString(), passwordEditText.text.toString())
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentLoginBinding = null
    }
}