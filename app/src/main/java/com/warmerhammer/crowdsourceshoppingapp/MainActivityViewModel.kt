package com.warmerhammer.crowdsourceshoppingapp


import android.util.Log
import androidx.lifecycle.ViewModel
import com.warmerhammer.crowdsourceshoppingapp.data.GroceryItem
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

    private val _currentPoints = MutableStateFlow(0)
    val currentPoints: StateFlow<Int> = _currentPoints.asStateFlow()
        fun setCurrentPoints(points: Int) {
            _currentPoints.value = points
        }

    private val _shoppingCartItems = MutableStateFlow(listOf<GroceryItem>())
    val shoppingCartItems: StateFlow<List<GroceryItem>> = _shoppingCartItems.asStateFlow()
        fun addShoppingCartItem(item: GroceryItem) {
            _shoppingCartItems.value += item
            Log.i("MainActivityViewModel", "addShoppingCartItem: ${_shoppingCartItems.value}")
        }
}
