package com.example.shopgo.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.shopgo.Models.Rating
import com.example.shopgo.Models.ShopGoModel
import com.example.shopgo.Services.ShopAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    private val shopAPIService = ShopAPIService()
    private val mDisposable = CompositeDisposable()

    val shopLivedata = MutableLiveData<List<ShopGoModel>>()


    fun refreshData() { //ana data class sor hem rate hemde shop u kapsayan
        getDataFromAPI()
    }

    private fun getDataFromAPI() {

        mDisposable.add(shopAPIService.getData().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<List<ShopGoModel>>() {
            override fun onSuccess(t: List<ShopGoModel>) {
                shopLivedata.value = t
            }

            override fun onError(e: Throwable) {
                Log.e("Hata", e.toString())
            }

        }))
    }

}