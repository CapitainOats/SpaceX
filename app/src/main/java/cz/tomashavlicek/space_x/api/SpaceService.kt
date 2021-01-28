package cz.tomashavlicek.space_x.api

import androidx.lifecycle.LiveData
import cz.tomashavlicek.space_x.util.LiveDataCallAdapterFactory
import cz.tomashavlicek.space_x.vo.Launch
import cz.tomashavlicek.space_x.vo.Rocket
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceService {

    @GET("rockets")
    fun getRockets(): LiveData<ApiResponse<List<Rocket>>>

    @GET("rockets/{id}")
    fun getRocket(
        @Path("id") id: String
    ): LiveData<ApiResponse<Rocket>>

    @GET("launches/{timebase}")
    fun getLaunches(
        @Path("timebase") timebase: String
    ): LiveData<ApiResponse<List<Launch>>>

    companion object {
        private const val BASE_URL = "https://api.spacexdata.com/v4/"

        fun create(): SpaceService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(SpaceService::class.java)
        }
    }
}
