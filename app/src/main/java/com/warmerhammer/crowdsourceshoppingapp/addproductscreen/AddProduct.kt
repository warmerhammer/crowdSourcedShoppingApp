package com.warmerhammer.crowdsourceshoppingapp.addproductscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun addProductScreen(
    onItemAdded: () -> Unit
) {
    TakePhoto {
        onItemAdded()
    }
}