package by.mbicycle.develop.retrofitquerypathandother

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LOG_TAG = "myLogs"
const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

class RetrofitManager(cityName: String, apiKey: String) {
    val retrofit = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    val messagesAPI = retrofit.create(MessagesAPI::class.java)
    val messages = messagesAPI.messages(cityName, apiKey)

    init {
        messages.enqueue(object : Callback<JsonParser> {
            override fun onResponse(call: Call<JsonParser>, response: Response<JsonParser>) {
                if (response.isSuccessful) {
                    Log.d(LOG_TAG, "name=${response.body()?.cityName}, temperature=${response.body()?.main?.temp}")
                } else {
                    Log.d(LOG_TAG, "response code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<JsonParser>, t: Throwable) {
                Log.d(LOG_TAG, "failure $t")
            }
        })
    }
}