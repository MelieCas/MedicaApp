package com.example.medicaapp.data


import com.example.medicaapp.Cites
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("/cites")
    suspend fun getCites(

    ): Cites
    @POST("/cita")
    suspend fun addCita(@Body cita : Cites): Response<Cites>
    @DELETE("/cita/{cita_id}")
    suspend fun deleteCita(@Path("cita_id") citaId: Int): Response<ResponseBody>
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder().baseUrl("https://13.216.181.56/").addConverterFactory(
            GsonConverterFactory.create()).build().create(RetrofitService::class.java)
    }
}