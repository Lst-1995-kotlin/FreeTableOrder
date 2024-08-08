package com.lst_1995.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            Toast.makeText(context, "Hello World", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_loginFragment_to_singInFragment)
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        // 자동 로그인 체크
        // 구글, 네이버, 카카오 계정으로 로그인 기능 작성
        // 디바이스가 무엇인지 설정하는 화면으로 전환 기능 작성
    }
}
