package com.lst_1995.login

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.lst_1995.core.domain.model.ModeType
import com.lst_1995.core.ui.BaseFragment
import com.lst_1995.login.databinding.FragmentSelectModeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectModeFragment : BaseFragment<FragmentSelectModeBinding>(R.layout.fragment_select_mode) {
    private val viewModel: SelectModeViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setObserver()
        setBackPress()
    }

    private fun setObserver() {
        viewModel.run {
            loginState.observe(viewLifecycleOwner) { isLogin ->
                if (isLogin == false) {
                    val navOption =
                        NavOptions.Builder().setPopUpTo(R.id.selectModeFragment, true).build()
                    findNavController().navigate(
                        R.id.action_selectModeFragment_to_loginFragment,
                        null,
                        navOption,
                    )
                }
            }
            selectMode.observe(viewLifecycleOwner) { mode ->
                if (mode != ModeType.NONE) {
                    val intent = Intent()
                    intent.component =
                        ComponentName(
                            "com.lst_1995.freetableorder",
                            "com.lst_1995.freetableorder.MainActivity",
                        )
                    startActivity(intent)
                    activity?.finish()
                }
            }
        }
    }

    private fun setBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.signOut()
        }
    }
}
