package com.gse.test.proyectqrhackaton.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gse.test.proyectqrhackaton.model.EnumApiStatus
import com.gse.test.proyectqrhackaton.model.repository.AppDemoRepository
import com.gse.test.proyectqrhackaton.model.request.AppDemoRequest
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PrincipalQrViewModel(private var appDemoRepository: AppDemoRepository) : ViewModel() {
    private val TAG: String? = PrincipalQrViewModel::class.simpleName


    fun callDemoConsume(context: Context, appDemoRequest: AppDemoRequest) = viewModelScope.launch {
        appDemoRepository.appPostDemo(context = context, appDemoRequest = appDemoRequest)
            .catch { e ->
                Log.e(TAG,"Failure: $e")
            }.collect { apiResponse ->
                if (apiResponse.status == EnumApiStatus.SUCCESS) {
                    Log.i(TAG,"Consumo exitoso")
                } else {
                    Log.e(TAG,"Consumo rechazado")
                }

            }
    }
}