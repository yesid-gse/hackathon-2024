package com.gse.test.proyectqrhackaton.model.repository

import android.content.Context
import com.gse.test.proyectqrhackaton.io.AppAdapter
import com.gse.test.proyectqrhackaton.model.request.AppDemoRequest
import com.gse.test.proyectqrhackaton.model.response.AppDemoResponse
import com.gse.test.proyectqrhackaton.model.statusControl.ApiResult
import com.gse.test.proyectqrhackaton.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class AppDemoRepository() {
    fun appPostDemo(
        context: Context,
        appDemoRequest: AppDemoRequest
    ): Flow<ApiResult<AppDemoResponse?>> = flow {
        val response: Response<AppDemoResponse>? =
            AppAdapter.getApiService(context)?.callApiDemo(request = appDemoRequest)

        response?.let {
            if (it.isSuccessful) {
                emit(
                    ApiResult.Success(
                        it.code(),
                        it.body()
                    )
                )
            } else {
                val errorMessage: String? = it.errorBody()?.string()
                response.errorBody()
                    ?.close()  // remember to close it after getting the stream of error body
                emit(
                    ApiResult.Failure(
                        it.code(),
                        Exception(errorMessage)
                    )
                )
            }
        }
            ?: run {
                emit(
                    ApiResult.Failure(
                        Constants.ERROR_CODE_DEFAULT,
                        Exception(Constants.ERROR_UNKNOWN)
                    )
                )
            }
    }.flowOn(Dispatchers.IO)
}