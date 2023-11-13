package com.warmerhammer.crowdsourceshoppingapp.addproductscreen

import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.DecimalFormat
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.warmerhammer.crowdsourceshoppingapp.BuildConfig
import com.warmerhammer.crowdsourceshoppingapp.data.GroceryItem
import com.warmerhammer.crowdsourceshoppingapp.groceryItems
import java.io.File
import java.util.Date
import java.util.Objects

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalCoilApi::class)
@Composable
fun TakePhoto(
    onItemAdded: () -> Unit
) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Row {
            Button(onClick = {
                val permissionCheckResult =
                    ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    cameraLauncher.launch(uri)
                } else {
                    // Request a permission
                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
            }) {
                Text(text = "Capture Image From Camera")
            }
        }

        if (capturedImageUri.path?.isNotEmpty() == true) {
            val productName = remember { mutableStateOf("") }
            val productNameTextColor = remember { mutableStateOf(Color.Red) }
            val price = remember { mutableStateOf("") }
            val description = remember { mutableStateOf("") }
            val storeName = remember { mutableStateOf("") }
            val storeNameTextColor = remember { mutableStateOf(Color.Red) }
            val scrollState = rememberScrollState()
            Column(
                Modifier
                    .verticalScroll(scrollState)
                    .padding(40.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row {
                    Image(
                        modifier = Modifier
                            .padding(16.dp, 8.dp)
                            .size(300.dp),
                        painter = rememberImagePainter(capturedImageUri),
                        contentDescription = null,
                        alignment = Alignment.Center
                    )
                }

                Column {
                    Text(
                        "Store",
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp,
                        color = storeNameTextColor.value
                    )
                    TextField(
                        value = storeName.value,
                        onValueChange = {
                            storeName.value = it
                            if (storeName.value.isNotEmpty()) {
                                storeNameTextColor.value = Color.Black
                            } else {
                                storeNameTextColor.value = Color.Red
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = Color.LightGray,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            textColor = MaterialTheme.colors.onBackground,
                        ),
                    )
                }

                Column {
                    Text(
                        "Product Name",
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp,
                        color = productNameTextColor.value
                    )
                    TextField(
                        value = productName.value,
                        onValueChange = {
                            productName.value = it
                            if (productName.value.isNotEmpty()) {
                                productNameTextColor.value = Color.Black
                            } else {
                                productNameTextColor.value = Color.Red
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = Color.LightGray,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            textColor = MaterialTheme.colors.onBackground,
                        )
                    )
                }
                Column {
                    Text(
                        "Price",
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp,
                    )
                    TextField(
                        value = price.value.toString(),
                        placeholder = { Text(text = "0.00") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { num -> price.value = num },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = Color.LightGray,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            textColor = MaterialTheme.colors.onBackground,
                        )
                    )
                }

                Column {
                    Text(
                        "Description",
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp,
                    )
                    TextField(
                        value = description.value,
                        onValueChange = { str -> description.value = str },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = Color.LightGray,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            textColor = MaterialTheme.colors.onBackground,
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        enabled = productName.value.isNotEmpty() && storeName.value.isNotEmpty(),
                        onClick = {
                            val newGroceryItem = GroceryItem(
                                name = productName.value,
                                description = description.value,
                                price = DecimalFormat("#.##").format(price.value.toFloat())
                                    .toFloat(),
                                image = capturedImageUri.toString()
                            )
                            groceryItems += newGroceryItem
                            onItemAdded()

                        }) {
                        Text(text = "Save Grocery Item")
                    }
                }

            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.N)
private fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}