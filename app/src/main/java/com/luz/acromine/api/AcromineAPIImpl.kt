package com.luz.acromine.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AcromineAPIImpl {
    private var URLBASE = "http://www.nactem.ac.uk/software/acromine/"

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URLBASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*fun getAcromineService() = provideRetrofit().create(IAcromineAPIService::class.java)*/
}