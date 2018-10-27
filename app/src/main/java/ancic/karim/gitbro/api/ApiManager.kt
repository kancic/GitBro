package ancic.karim.gitbro.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    val gson = GsonBuilder().create()
    val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()
    val retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl("https://api.github.com").client(okHttpClient).build()
    val service = retrofit.create(ApiService::class.java)

    companion object {
        var defaultInstance: ApiManager? = null

        fun getInstance(): ApiManager {
            if (defaultInstance == null) defaultInstance = ApiManager()
            return defaultInstance!!
        }
    }

}
