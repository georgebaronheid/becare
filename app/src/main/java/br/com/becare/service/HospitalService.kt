package br.com.becare.service

import br.com.becare.entities.dtos.Hospital
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HospitalService {

    @GET("{keyword}")
    fun getBySearchKeyword(@Path("keyword") keyword: String): Call<Array<Hospital>>

}
