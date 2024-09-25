package com.lst_1995.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lst_1995.core.domain.util.NetworkManager
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    @Inject
    lateinit var networkManager: NetworkManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setNetworkStateObserver()
        return binding.root
    }

    private fun setNetworkStateObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                networkManager.networkStateFlow().collect { state ->
                    if (!state) {
                        val dialog = MaterialAlertDialogBuilder(requireContext())
                        dialog.setMessage(resources.getString(R.string.network_error_message))
                        dialog.setPositiveButton(resources.getString(R.string.check)) { dialog, _ ->
                            dialog.dismiss()
                            requireActivity().finish()
                        }
                        dialog.show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun showToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun setBackStackByToolbar(toolbar: MaterialToolbar) {
        toolbar.setNavigationOnClickListener {
            setupBackStack()
        }
    }

    protected fun setBackStackByButton(button: Button) {
        button.setOnClickListener {
            setupBackStack()
        }
    }

    private fun setupBackStack() {
        findNavController().popBackStack()
    }
}
