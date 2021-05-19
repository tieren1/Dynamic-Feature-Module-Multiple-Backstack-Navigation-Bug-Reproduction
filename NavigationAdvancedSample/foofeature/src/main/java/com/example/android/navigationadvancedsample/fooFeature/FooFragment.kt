package com.example.android.navigationadvancedsample.fooFeature

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.security.KeyStore
import java.security.Signature

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ECDSA_SIGNATURE_FORMAT = "SHA256withECDSA"
private const val RSA_SIGNATURE_FORMAT = "SHA256withRSA"
private const val ECDSA_SIGNATURE_FORMAT_NO_HASH = "NONEwithECDSA"
private const val RSA_SIGNATURE_FORMAT_NO_HASH = "NONEwithRSA"
private const val androidKeyStoreProvider = "AndroidKeyStore"
private const val AUTH_ALIAS = "authCertAlias"

/**
 * A simple [Fragment] subclass.
 * Use the [FooFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FooFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    internal val androidKeyStore: KeyStore by lazy {
        KeyStore.getInstance(androidKeyStoreProvider).apply { load(null) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fooButton = view.findViewById<Button>(R.id.fooButton)
        fooButton?.setOnClickListener {
            val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            val newMode = when (currentNightMode) {
                // Night mode is not active, we're in day time
                // We don't know what mode we're in, assume notnight
                UI_MODE_NIGHT_NO, UI_MODE_NIGHT_UNDEFINED -> AppCompatDelegate.MODE_NIGHT_YES
                // Night mode is active, we're at night!
                UI_MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO

                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
            AppCompatDelegate.setDefaultNightMode(newMode)
        }

        val certButton = view.findViewById<Button>(R.id.certButton)
        certButton?.setOnClickListener {
                findNavController().navigate(R.id.action_fooScreen_to_barScreen)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FooFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FooFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
