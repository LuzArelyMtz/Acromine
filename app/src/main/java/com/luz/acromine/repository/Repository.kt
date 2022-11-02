package com.luz.acromine.repository

import com.luz.acromine.api.model.ResponseItem

interface Repository {
    suspend fun acronymList(term: String): ResponseItem?
}