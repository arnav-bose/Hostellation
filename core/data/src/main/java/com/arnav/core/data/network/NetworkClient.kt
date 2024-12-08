package com.arnav.core.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    const val BASE_URL = "https://gist.githubusercontent.com/PedroTrabulo-Hostelworld/"

    private var networkClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    fun initializeNetworkClient() {
        if (networkClient == null) {
            networkClient = OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                ))
                .client(networkClient!!)
                .build()
        }
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit?.create(serviceClass) ?: throw Exception("Retrofit not initialized")
    }

}