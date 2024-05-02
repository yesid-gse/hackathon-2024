package com.gse.test.proyectqrhackaton.io

import android.content.Context
import android.util.Log
import com.gse.test.proyectqrhackaton.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppAdapter {

    companion object {
        private var API_SERVICE: AppApiService? = null
        private var TAG = AppAdapter::class.java.simpleName
        fun getApiService(context: Context): AppApiService? {
            val customLogger: HttpLoggingInterceptor.Logger =
                object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.d(TAG, "OkHttp: $message")
                    }
                }
            val logging = HttpLoggingInterceptor(customLogger)
                .setLevel(HttpLoggingInterceptor.Level.BODY)


            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            httpClient.writeTimeout(5, TimeUnit.MINUTES) // write timeout
            httpClient.readTimeout(5, TimeUnit.MINUTES) // read timeout


            if (API_SERVICE == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Constants.API_SERVER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        httpClient
                            .addInterceptor(AppInterceptor(context))
                            .build()
                    )
                    .build()
                API_SERVICE = retrofit.create(AppApiService::class.java)
            }

            return API_SERVICE
        }
    }
}