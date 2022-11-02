package com.luz.acromine.api

import com.luz.acromine.api.model.APIResult
import retrofit2.http.GET
import retrofit2.http.Query

interface IAcromineAPIService {
    @GET("dictionary.py")
    suspend fun getResponse(@Query("sf") term: String): APIResult
}