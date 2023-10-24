package com.warmerhammer.crowdsourceshoppingapp


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _currentpage = MutableStateFlow("homescreen")
    val currentpage: StateFlow<String> = _currentpage.asStateFlow()
        fun setCurrentPage(destination: String) {
            _currentpage.value = destination
        }
}
