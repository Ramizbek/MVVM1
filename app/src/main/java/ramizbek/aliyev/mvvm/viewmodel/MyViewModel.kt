package ramizbek.aliyev.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ramizbek.aliyev.mvvm.repository.MyDataRepository
import ramizbek.aliyev.mvvm.utils.Resource

class MyViewModel(val myDataRepository: MyDataRepository) : ViewModel() {

    private val liveData = MutableLiveData<Resource<String>>()


    init {
        loadString()
    }

    fun loadString(indexString: String? = null) {

        viewModelScope.launch {

            try {

                var  index :Int? = -1
                if (indexString == null){
                    index = null
                }else{
                    index = indexString.toInt()
                }

                if (index == null) {
                    liveData.postValue(Resource.loading(null))
                } else {
                    if (index >= 0 && index < myDataRepository.getSize()) {
                        liveData.postValue(Resource.success(myDataRepository.getData(index)))
                    } else {
                        liveData.postValue(Resource.error("Kiritilgan Indexda ma'lumot yoq", null))
                    }
                }
            }catch (e:Exception){
                liveData.postValue(Resource.error("Index kiriting", null))
            }
        }
    }

    fun getData() = liveData

}