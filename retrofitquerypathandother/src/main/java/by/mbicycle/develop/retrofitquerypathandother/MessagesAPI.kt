package by.mbicycle.develop.retrofitquerypathandother

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MessagesAPI {
    @GET("weather?units=metric")
    fun messages(@Query("q") cityName: String, @Query("appid") apiKey: String) : Call<JsonParser>
}