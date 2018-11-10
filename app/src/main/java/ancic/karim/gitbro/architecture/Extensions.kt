package ancic.karim.gitbro.architecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

fun <T> LiveData<T?>.nonNull(): LiveData<T> {
    return MediatorLiveData<T>().apply {
        addSource(this@nonNull) { data ->
            data?.let { value = data }
        }
    }
}

fun <X, Y> LiveData<X>.map(func: (X) -> Y): MutableLiveData<Y> {
    return Transformations.map(this, func) as MutableLiveData<Y>
}

fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>): MutableLiveData<Y> {
    return Transformations.switchMap(this, func) as MutableLiveData<Y>
}