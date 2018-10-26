package ancic.karim.gitbro.architecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class LiveField<T>() : MutableLiveData<T>() {
    private val mediatorLiveData = MediatorLiveData<T>()

    constructor(initialValue: T) : this() {
        setValue(initialValue)
    }

    constructor(vararg liveDataList: LiveData<*>, code: (Any?) -> T) : this() {
        liveDataList.forEach { liveData ->
            mediatorLiveData.addSource(liveData) { data -> setValue(code.invoke(data)) }
        }
    }

    override fun setValue(value: T) {
        if (value != getValue()) {
            super.setValue(value)
        }
    }
}
