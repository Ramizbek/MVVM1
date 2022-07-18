package ramizbek.aliyev.mvvm.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        // message ni faqat biror narsa xabar berish uchun ishalatamiz masalan Error

        fun <T> error(message: String?, data:T?):Resource<T>{
            return Resource(Status.ERROR, data, message)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
