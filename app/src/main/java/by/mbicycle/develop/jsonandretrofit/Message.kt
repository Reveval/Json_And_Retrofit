package by.mbicycle.develop.jsonandretrofit

//Создаем класс, поля которого будут соответствовать полям нашего json файла
data class Message(val id: Long, val time: Long, val text: String, val image: String)