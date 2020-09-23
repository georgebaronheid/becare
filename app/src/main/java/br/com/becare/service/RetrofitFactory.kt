package br.com.becare.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    val URL: String = "https://becare-api.azurewebsites.net/api/hospitais/listar/"

    val retrofitFactory = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun hospitalService(): HospitalService {
        return retrofitFactory.create(HospitalService::class.java)
    }
}
