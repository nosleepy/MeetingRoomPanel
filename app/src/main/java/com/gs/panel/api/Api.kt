package com.gs.panel.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

object Api {
    private const val BASE_URL = "https://192.168.124.20:8089/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
//            .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(CustomApplication.context))) // cookie持久化
            .hostnameVerifier { hostname, session -> true }
            .build()
    }

    private val api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(IApi::class.java)
    }

    fun get(): IApi {
        return api
    }
}