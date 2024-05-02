package com.gse.test.proyectqrhackaton.io

import android.content.Context
import android.content.ContextWrapper
import com.gse.test.proyectqrhackaton.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor(private var context: Context)   : ContextWrapper(context), Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        print("intercept $context")
        var newRequest: okhttp3.Request = chain.request()
        newRequest = newRequest.newBuilder()
            .addHeader(Constants.CONTENT_TYPE_HEADER, Constants.CONTENT_TYPE_VALUE_HEADER)
            .build()

        return chain.proceed(newRequest)
    }


}