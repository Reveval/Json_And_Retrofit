package by.mbicycle.develop.retrofitquerypathandother

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JsonParser(
    @SerializedName("main")
    @Expose
    val main: Main,
    @SerializedName("name")
    @Expose
    val cityName: String
)

data class Main(
    @SerializedName("temp")
    @Expose
    val temp: Double
)
