package com.target.androidcasestudy.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.target.androidcasestudy.R
import com.target.androidcasestudy.database.AppDatabase
import com.target.androidcasestudy.viewmodel.ItemViewModel

class MainActivity : AppCompatActivity() {

    var itemViewModel: ItemViewModel?=null
    var itemFragment:ItemFragment?=null
    val TAG=javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate")

        val fragmentManager=supportFragmentManager
        itemFragment=ItemFragment.newInstance()
        itemFragment?.let {
            fragmentManager.beginTransaction().add(R.id.fragment_container,it,ItemFragment.javaClass.simpleName).commit()

        }

        itemViewModel = ViewModelProviders.of(this)[ItemViewModel::class.java]
        var appDatabase = AppDatabase.getDatabase(this)
        if(isOnline()) {
            itemViewModel?.getItemListFromNw(appDatabase)
        }
        else{
            itemViewModel?.getItemListFromDb(appDatabase)
        }
        itemViewModel?.itemLiveData?.observe(this, Observer {
            Log.d("onLiveDataChange","live data changed")

            itemFragment?.getItemList(it)
        })


    }

    fun isOnline(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy ")
    }



}
