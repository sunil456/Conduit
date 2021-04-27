package io.sunil.conduit.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.sunil.conduit.databinding.FragmentFeedBinding
import io.sunil.conduit.databinding.FragmentSettingsBinding
import io.sunil.conduit.ui.auth.AuthViewModel

class SettingsFragment : Fragment() {

    private val authViewModel by activityViewModels<AuthViewModel>()

    private var _fragmentSettingsFragment: FragmentSettingsBinding? = null

    private val settingsBinding get() = _fragmentSettingsFragment!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentSettingsFragment = FragmentSettingsBinding.inflate(inflater, container, false)
        return settingsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe({ lifecycle }){
            settingsBinding.apply {
                bioEditText.setText(it?.bio ?: "")
                emailEditText.setText(it?.email ?: "")
                userNameEditText.setText(it?.username ?: "")
                imageEditText.setText(it?.image ?: "")
            }
        }

        settingsBinding.apply {
            submitButton.setOnClickListener {
                authViewModel.update(
                       bio =  bioEditText.text.toString().takeIf { it.isNotBlank() },
                       email = emailEditText.text.toString().takeIf { it.isNotBlank() },
                       userName =  userNameEditText.text.toString().takeIf { it.isNotBlank() },
                       image =  imageEditText.text.toString().takeIf { it.isNotBlank() },
                       password =  passwordEditText.text.toString().takeIf { it.isNotBlank() }
                )
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentSettingsFragment = null
    }
}