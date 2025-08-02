package com.fit2081.Tee_34570403.nutritrack.data.repositories

import com.fit2081.Tee_34570403.nutritrack.data.network.FruityViceAPIService
import com.fit2081.Tee_34570403.nutritrack.data.network.FruityViceResponseModel
import retrofit2.http.Path

class FruityViceRepository() {

    private val apiService = FruityViceAPIService.create()

    suspend fun getFruitInfo(name :String): FruityViceResponseModel? {
        return apiService.getFruitInfo(name).body();
    }
}