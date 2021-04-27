package io.sunil.conduit.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import io.sunil.conduit.R
import io.sunil.conduit.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private var _fragmentAuthBinding : FragmentAuthBinding? = null
    private var navController: NavController? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val authBinding get() = _fragmentAuthBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _fragmentAuthBinding = FragmentAuthBinding.inflate(inflater, container, false)

        return authBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { navController = Navigation.findNavController(it, R.id.authFragmentNavigation) }


        authBinding.authTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){

                    0 -> {
                        navController?.navigate(R.id.goToLoginFragment)
                    }
                    1 -> {
                        navController?.navigate(R.id.goToSignupFragment)
                    }
                    else ->{
                        navController?.navigate(R.id.goToLoginFragment)
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentAuthBinding = null
    }
}