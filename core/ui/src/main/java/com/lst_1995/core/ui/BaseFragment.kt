package com.lst_1995.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseFragment<VB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun createDialogForMessage(message: String) {
        val dialog = MaterialAlertDialogBuilder(requireContext())
        dialog.setMessage(message)
        dialog
            .setPositiveButton(R.string.check) { _, _ ->
                findNavController().popBackStack()
            }.setOnDismissListener {
                findNavController().popBackStack()
            }
        dialog.show()
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
