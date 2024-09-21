package com.lst_1995.core.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val _loadingProgressVisibility = MutableLiveData(View.GONE)
    val loadingProgressVisibility: LiveData<Int> get() = _loadingProgressVisibility

    private val _clickable = MutableLiveData(true)
    val clickable: LiveData<Boolean> get() = _clickable

    protected fun setLoadingState(isLoading: Boolean) {
        if (isLoading) {
            _loadingProgressVisibility.value = View.VISIBLE
            _clickable.value = false
        } else {
            _loadingProgressVisibility.value = View.GONE
            _clickable.value = true
        }
    }
}
