package ramizbek.aliyev.mvvm.repository

import ramizbek.aliyev.mvvm.models.MyData

class MyDataRepository(val myData: MyData) {

    suspend fun getData(index:Int) = myData.getData(index)
    suspend fun getSize() = myData.getSize()
}