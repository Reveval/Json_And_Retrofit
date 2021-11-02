package by.mbicycle.develop.jsonandretrofit

import retrofit2.Call
import retrofit2.http.GET

//Создаем интерфейс для работы с ссылкой
interface MessagesAPI {
    /*
        В аннотации GET указываем, что к базовому URL надо будет добавить "messages1.json",
            чтобы получилась нужная нам ссылка.
     */
    @GET("messages1.json")
    /*
        Call - обертка нужна для работы Retrofit. В ней мы указываем, какой тип данных ожидаем
            получить из messages1.json - т.е. List<Message>.
     */
    fun messages() : Call<List<Message>>
}