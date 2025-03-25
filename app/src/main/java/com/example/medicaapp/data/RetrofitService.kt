package com.example.medicaapp.data


import com.example.medicaapp.Cites
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


interface RetrofitService {
    @GET("/cites")
    suspend fun getCites(

    ): MutableList<Cites>
    @POST("/cita")
    suspend fun addCita(@Body cita : Cites): Response<ResponseBody>
    @PUT("/cita/{cita_id}")
    suspend fun updateCita(@Path("cita_id") citaId: Int, @Body cita: Cites): Response<Cites>
    @DELETE("/cita/{cita_id}")
    suspend fun deleteCita(@Path("cita_id") citaId: Int): Response<ResponseBody>
}

private fun getUnsafeOkHttpClient(): OkHttpClient {
    try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
        )

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory)
        builder.hostnameVerifier { hostname, session -> true }

        val okHttpClient = builder.build()
        return okHttpClient
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder().baseUrl("https://13.216.181.56/").addConverterFactory(
            GsonConverterFactory.create()).client(getUnsafeOkHttpClient()).build().create(RetrofitService::class.java)
    }
}