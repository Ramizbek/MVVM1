package ramizbek.aliyev.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ramizbek.aliyev.mvvm.repository.MyDataRepository

class ViewModelFactory(
    private val myDataRepository: MyDataRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(myDataRepository) as T
        }
        throw IllegalArgumentException("Error")
    }
}