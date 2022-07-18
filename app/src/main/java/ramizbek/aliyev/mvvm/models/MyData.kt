package ramizbek.aliyev.mvvm.models

class MyData {
    private val list  = arrayOf("Java", "Kotlin", "Android")

    suspend fun getData(index:Int):String = list[index]
    suspend fun getSize():Int = list.size
}