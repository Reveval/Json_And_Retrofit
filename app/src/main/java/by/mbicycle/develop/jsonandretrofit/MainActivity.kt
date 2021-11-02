package by.mbicycle.develop.jsonandretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LOG_TAG = "myLogs"

class MainActivity : AppCompatActivity() {
    /*
        В билдере мы указываем базовый URL и добавляем Gson конвертер, чтобы Retrofit сам смог
            сконвертировать json данные в объекты Message. Это минимум настроек, который от нас
            требуется для нашего простого примера. Для сложных случаев количество параметров может
            быть больше.
     */
    val retrofit = Retrofit.Builder().apply {
        baseUrl("https://rawgit.com/startandroid/data/master/messages/")
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    /*
        В итоге у нас есть объект Retrofit, который содержит базовый URL и способность
            преобразовывать json данные с помощью Gson. Мы передаем ему в метод create класс
            интерфейса, в котором описывали методы.
            И получаем реализацию MessagesApi от Retrofit. В этой реализации соединены настройки
            билдера (базовый URL и Gson конвертер) и описания методов из интерфейса (метод messages
            для получения файла messages1.json). В итоге Retrofit будет брать базовый URL из
            билдера, присоединять к нему остаток пути, который мы указываем в GET в интерфейсе, и
            тем самым получит полную ссылку.
     */
    val messagesAPI = retrofit.create(MessagesAPI::class.java)
    //Используем MessagesAPI для получения данных. Вызываем метод messages()
    val messages = messagesAPI.messages()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
            Вызов метода messages вернет нам Call объект. При выполнении этого кода запрос к
                серверу еще не был отправлен. Call дает нам возможность отправить запрос синхронно
                (блокируя текущий поток) или асинхронно (с колбэком). Мы выбираем асинхронный
                вариант: вызываем метод enqueue и создаем для него Callback. Запрос будет выполнен
                в отдельном потоке, а результат придет в Callback в main потоке.
         */
        messages.enqueue(object : Callback<List<Message>> {
            /*
                Если взаимодействие с сервером пройдет успешно, то мы получим результат в метод
                    onResponse в объекте Response. Чтобы добраться до данных, необходимо вызвать
                    метод body() у Response объекта. Это даст нам список сообщений - List<Message>.
                    Выводим в лог количество записей в этом списке.
                    Если запрос был выполнен успешно (isSuccessfull), то выводим в лог количество
                    полученных записей. Иначе выводим в лог код ошибки.
             */
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    Log.d(LOG_TAG, "response ${response.body()?.size}")
                } else {
                    Log.d(LOG_TAG, "response code ${response.code()}")
                }
            }

            /*
                Если по каким-то причинам не удалось достучаться до сервера, то вместо onResponse,
                    будет вызван метод onFailure. Добавим вывод в лог ошибки, которую вернет
                    нам Retrofit
             */
            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                Log.d(LOG_TAG, "failure $t")
            }
        })
    }
}