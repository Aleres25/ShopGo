package com.example.shopgo.Services

import com.example.shopgo.Models.ShopGoModel
import io.reactivex.Single
import retrofit2.http.GET

interface ShopAPI {

    //https://fakestoreapi.com/products
        @GET("products")
        fun getShops():Single<List<ShopGoModel>>
}