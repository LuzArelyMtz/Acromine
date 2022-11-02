package com.luz.acromine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luz.acromine.api.AcromineAPIImpl
import com.luz.acromine.api.model.Lf
import com.luz.acromine.repository.AcromineRepository
import kotlinx.coroutines.launch

class AcromineViewModel : ViewModel() {
    private var _livedataAcronym = MutableLiveData<List<Lf>>()
    var livedataAcronym: LiveData<List<Lf>> = _livedataAcronym

    private var _searchProgress = MutableLiveData<Boolean>()
    val searchProgress: LiveData<Boolean> = _searchProgress

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val service = AcromineAPIImpl()
    private val acromineRepository: AcromineRepository by lazy { AcromineRepository(service) }

    fun getAcronymList(s: String) {

        viewModelScope.launch {
            _searchProgress.postValue(true)

            try {
                val response = acromineRepository.acronymList(s)
                if (response != null) {
                    _livedataAcronym.value = response.lfs
                } else {
                    _error.value = "Not results found"
                }
                _searchProgress.postValue(false)

            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Network Error"
            }
            _searchProgress.postValue(false)
        }
    }
}