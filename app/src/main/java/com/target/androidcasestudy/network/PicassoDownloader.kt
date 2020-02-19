package com.target.androidcasestudy.network

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object PicassoDownloader {

    fun getPicasso( context: Context):Picasso{
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
        val okHttp3Downloader = OkHttp3Downloader(client)
        return Picasso.Builder(context).downloader(okHttp3Downloader).build()
    }
}