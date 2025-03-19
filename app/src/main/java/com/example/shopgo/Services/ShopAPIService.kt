package com.example.shopgo.Services

import com.example.shopgo.Models.ShopGoModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ShopAPIService {
    private val BASE_URL = "https://fakestoreapi.com/"
    private val api = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(ShopAPI::class.java)


    fun getData(): Single<List<ShopGoModel>> {
        return api.getShops()
    }
}