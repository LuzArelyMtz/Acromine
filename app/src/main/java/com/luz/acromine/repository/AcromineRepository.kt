package com.luz.acromine.repository

import com.luz.acromine.api.AcromineAPIImpl
import com.luz.acromine.api.model.ResponseItem

class AcromineRepository(private val acromineService: AcromineAPIImpl) : Repository {
    override suspend fun acronymList(term: String): ResponseItem? {
        val response = acromineService.getAcromineService().getResponse(term)
        return if (response.size > 0) {
            response[0]
        } else {
            null
        }
    }
}