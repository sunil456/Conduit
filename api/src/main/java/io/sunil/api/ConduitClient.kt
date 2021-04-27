package io.sunil.api

import io.sunil.api.services.ConduitApi
import io.sunil.api.services.ConduitAuthAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ConduitClient {

    var authToken: String? = null

    private val authInterceptor = Interceptor { chain ->
        var req = chain.request()

        authToken?.let {
            req = req.newBuilder()
                    .header("Authorization", "Token $it")
                    .build()
        }

        chain.proceed(req)
    }

    private val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(2, TimeUnit.SECONDS)

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://conduit.productionready.io/api/")
        .addConverterFactory(MoshiConverterFactory.create())

    val publicApi = retrofitBuilder
            .client(okHttpBuilder.build())
            .build()
            .create(ConduitApi::class.java)!!

    val authApi = retrofitBuilder
            .client(okHttpBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(ConduitAuthAPI::class.java)!!
}