package by.mbicycle.develop.retrofitquerypathandother

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val API_KEY = "ae1d7412fa4758a0fc38da4d2040c40b"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitManager("London", API_KEY)
    }
}