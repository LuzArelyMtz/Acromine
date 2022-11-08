package com.luz.acromine.repository

import com.luz.acromine.api.AcromineAPIImpl
import com.luz.acromine.api.IAcromineAPIService
import com.luz.acromine.api.model.ResponseItem
import javax.inject.Inject

class AcromineRepository @Inject constructor(private val acromineService: IAcromineAPIService) : Repository {
    override suspend fun acronymList(term: String): ResponseItem? {
        val response = acromineService.getResponse(term)
        return if (response.size > 0) {
            response[0]
        } else {
            null
        }
    }
}