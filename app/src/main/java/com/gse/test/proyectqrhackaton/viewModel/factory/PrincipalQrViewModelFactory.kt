package com.gse.test.proyectqrhackaton.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gse.test.proyectqrhackaton.model.repository.AppDemoRepository
import com.gse.test.proyectqrhackaton.viewModel.PrincipalQrViewModel

class PrincipalQrViewModelFactory( private val appDemoRepository: AppDemoRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  PrincipalQrViewModel(appDemoRepository) as T
    }
}