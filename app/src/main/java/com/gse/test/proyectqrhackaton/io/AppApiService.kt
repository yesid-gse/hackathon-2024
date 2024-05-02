package com.gse.test.proyectqrhackaton.io

import com.gse.test.proyectqrhackaton.model.request.AppDemoRequest
import com.gse.test.proyectqrhackaton.model.response.AppDemoResponse
import com.gse.test.proyectqrhackaton.model.response.GeneralResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AppApiService {

    @POST("/posts")
    suspend fun callApiDemo(@Body request: AppDemoRequest): Response<AppDemoResponse>
}