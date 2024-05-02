package com.gse.test.proyectqrhackaton.util

class Constants {
    companion object {
        //   'Content-type': 'application/json; charset=UTF-8'
        val API_SERVER_BASE_URL: String = "https://jsonplaceholder.typicode.com"
        val CONTENT_TYPE_HEADER: String = "Content-type"
        val CONTENT_TYPE_VALUE_HEADER: String = "application/json; charset=UTF-8"
        const val ERROR_CODE_DEFAULT: Int = 500
        const val ERROR_UNKNOWN: String = "Unknown error"
    }

}