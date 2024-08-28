package com.lst_1995.login

import android.app.PendingIntent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()

    private lateinit var signInClient: SignInClient
    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            try {
                val idToken = signInClient.getSignInCredentialFromIntent(result.data).googleIdToken
                if (idToken != null) {
                    viewModel.firebaseAuthWithGoogle(idToken)
                } else {
                    Log.d(TAG, "No ID token!")
                }
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed / signInLauncher", e)
            }
        }
    private val signInRequest =
        GetSignInIntentRequest
            .builder()
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setObserver()
        setLoginWithGoogle()
    }

    override fun onStart() {
        super.onStart()
        binding.googleLoginIV.isClickable = true
    }

    override fun onStop() {
        super.onStop()
        binding.googleLoginIV.isClickable = false
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.loginState.collect { state ->
                        if (state) {
                            findNavController().navigate(R.id.action_loginFragment_to_selectModeFragment)
                        }
                    }
                }
            }
        }
    }

    private fun setLoginWithGoogle() {
        binding.googleLoginIV.setOnClickListener {
            googleWithSignIn()
        }
    }

    private fun googleWithSignIn() {
        signInClient = Identity.getSignInClient(requireContext())
        signInClient
            .getSignInIntent(signInRequest.build())
            .addOnSuccessListener { pendingIntent ->
                launchSignIn(pendingIntent)
            }.addOnFailureListener { e ->
                Log.e(TAG, "Google Sign-in failed / googleWithSignIn()", e)
            }
    }

    private fun launchSignIn(pendingIntent: PendingIntent) {
        try {
            val intentSenderRequest =
                IntentSenderRequest
                    .Builder(pendingIntent)
                    .build()
            signInLauncher.launch(intentSenderRequest)
        } catch (e: IntentSender.SendIntentException) {
            Log.e(TAG, "Couldn't start Sign In: ${e.localizedMessage}")
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}
