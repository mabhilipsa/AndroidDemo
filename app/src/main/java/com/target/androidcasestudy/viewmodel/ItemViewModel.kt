package com.target.androidcasestudy.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.androidcasestudy.database.AppDatabase
import com.target.androidcasestudy.model.Item
import com.target.androidcasestudy.model.ResponseModel
import com.target.androidcasestudy.network.ApiBuilder
import com.target.androidcasestudy.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemViewModel: ViewModel() {

    var itemLiveData= MutableLiveData<List<Item>>()

    fun getItemListFromNw(appDatabase: AppDatabase){

        ApiBuilder.getApiBuilder().create(ApiInterface::class.java).getItemList()
            .enqueue(object : Callback<ResponseModel> {
                override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                    if (response.isSuccessful) {
                        Log.d("success",response.body().toString())
                      response.body()?.data?.let {
                          itemLiveData.value = it
                          GlobalScope.launch {
                              appDatabase.itemDao().insertAll(it)
                          }
                      }
                    } else {
                        Log.d("failure",response.message())
                    }
                }
                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Log.d("failure",""+t.message)
                }
            })
    }

     fun getItemListFromDb(appDatabase: AppDatabase){
         GlobalScope.launch {
             var employeeList = appDatabase.itemDao().getALL()
             GlobalScope.launch(Dispatchers.Main) {
                 if(!employeeList.isNullOrEmpty())
                     itemLiveData.value = employeeList
             }

         }

     }



}


