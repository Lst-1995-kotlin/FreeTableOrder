package com.lst_1995.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.login.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            Toast.makeText(context, "Hello World", Toast.LENGTH_SHORT).show()
        }
    }
}
