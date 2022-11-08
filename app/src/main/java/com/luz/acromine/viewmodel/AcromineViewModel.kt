package com.luz.acromine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luz.acromine.api.AcromineAPIImpl
import com.luz.acromine.api.model.Lf
import com.luz.acromine.repository.AcromineRepository
import com.luz.acromine.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AcromineViewModel @Inject constructor(private val repository : Repository) : ViewModel() {
    private var _livedataAcronym = MutableLiveData<List<Lf>>()
    var livedataAcronym: LiveData<List<Lf>> = _livedataAcronym

    private var _searchProgress = MutableLiveData<Boolean>()
    val searchProgress: LiveData<Boolean> = _searchProgress

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getAcronymList(s: String) {

        viewModelScope.launch(Dispatchers.IO) {
            _searchProgress.postValue(true)

            try {
                val response = repository.acronymList(s)
                if (response != null) {
                    _livedataAcronym.postValue(response.lfs)
                } else {
                    _error.postValue("Not results found")
                }
                _searchProgress.postValue(false)

            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue("Network Error")
            }
            _searchProgress.postValue(false)
        }
    }
}