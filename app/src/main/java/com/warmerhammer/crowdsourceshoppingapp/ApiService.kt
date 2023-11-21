package com.warmerhammer.crowdsourceshoppingapp

import android.widget.Toast
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIServices {

    // Example Call

//    private fun addItemToServer(){
//        RetrofitApp.getInstance().getApiService().addItem().enqueue(object : Callback<Item> {
//            override fun onResponse(call: Call<Item>, response: Response<Item>) {
//                // ... Handle data from response
//            }
//
//            override fun onFailure(call : Call<Item>, t: Throwable) {
//                Toast.makeText(this@AddItem, "Failed to post", Toast.LENGTH_LONG)
//                    .show()
//            }
//        })
//    }

    @GET("/api/v1/items{id}/")
    fun getItemID(@Path("id") id:Int): Response<Item>

    @POST("/api/v1/items/create/")
    fun createItem(@Body body: JsonObject): Response<JsonObject>

    @DELETE("/api/v1/items/delete/{id}/")
    fun deleteItem(@Path("id") id:Int): Response<JsonObject>

    @PUT("/api/v1/items/update/{id}/")
    fun updateItem(@Path("id") id:Int, @Body body: JsonObject): Response<JsonObject>

    @GET("/api/v1/products/")
    fun getProducts(): Response<List<Product>>

    @GET("/api/v1/products/{id}/")
    fun getProductID(@Path("id") id:Int): Response<Int>

    @POST("/api/v1/products/create/")
    fun createProduct(@Body body: JsonObject): Response<JsonObject>

    @DELETE("/api/v1/products/delete/{id}/")
    fun deleteProduct(@Path("id") id:Int): Response<JsonObject>

    @PUT("/api/v1/products/update/{id}/")
    fun updateProduct(@Path("id") id:Int, @Body body: JsonObject): Response<JsonObject>

    @GET("/api/v1/shoppinglistitems/list/{id}")
    fun getShoppingLists(): Response<List<ShoppingList>>

    @GET("/api/v1/shoppinglistitems/{id}")
    fun getShoppingListID(@Path("id") id:Int): Response<Int>

    @POST("/api/v1/shoppinglistitems/create")
    fun createShoppingList(@Body body: JsonObject): Response<JsonObject>

    @DELETE("/api/v1/shoppinglistitems/delete/{id}")
    fun deleteShoppingList(@Path("id") id:Int): Response<JsonObject>

    @PUT("/api/v1/shoppinglistitems/update/{id}")
    fun updateShoppingList(@Path("id") id:Int, @Body body: JsonObject): Response<JsonObject>

    @GET("/api/v1/stores")
    fun getStores(): Response<List<Stores>>

    @GET("/api/v1/stores/{id}")
    fun getStoreID(@Path("id") id:Int): Response<Int>

    @POST("/api/v1/stores/create/")
    fun createStore(@Body body: JsonObject): Response<JsonObject>

    @DELETE("/api/v1/stores/delete/{id}")
    fun deleteStore(@Path("id") id:Int): Response<JsonObject>

    @PUT("/api/v1/stores/update/{id}")
    fun updateStore(@Path("id") id:Int, @Body body: JsonObject): Response<JsonObject>

    @GET("/api/v1/users")
    fun getUsers(): Response<List<Stores>>

    @GET("/api/v1/users/{id}")
    fun getUserID(@Path("id") id:Int): Response<Int>

    @POST("/api/v1/users/create")
    fun createUser(@Body body: JsonObject): Response<JsonObject>

    @DELETE("/api/v1/users/delete/{id}")
    fun deleteUser(@Path("id") id:Int): Response<JsonObject>

    @PUT("/api/v1/users/update/{id}")
    fun updateUser(@Path("id") id:Int, @Body body: JsonObject): Response<JsonObject>

}