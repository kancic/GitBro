package ancic.karim.gitbro.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    companion object {
        val gson: Gson by lazy { GsonBuilder().create() }
        val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
        }
        val retrofit: Retrofit by lazy {
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl("https://api.github.com").client(okHttpClient).build()
        }
        val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }
    }
}
