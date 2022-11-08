package com.luz.acromine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luz.acromine.repository.Repository
import javax.inject.Inject


class AcromineViewModelFactory @Inject constructor(
    private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AcromineViewModel(repository) as T
    }
}