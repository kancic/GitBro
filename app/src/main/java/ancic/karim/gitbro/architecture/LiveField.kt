package ancic.karim.gitbro.architecture

import androidx.annotation.MainThread
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

class LiveField<T>() : MediatorLiveData<T>() {
    constructor(initialValue: T) : this() {
        setValue(initialValue)
    }

    @MainThread
    constructor(vararg source: LiveData<*>, mapFunction: (Any?) -> T) : this() {
        source.forEach {
            addSource(it) { x -> setValue(mapFunction.invoke(x)) }
        }
    }

    @MainThread
    constructor(vararg source: LiveData<*>, switchMapFunction: Function<Any?, LiveData<T?>>) : this() {
        var oldSource: LiveData<T?>? = null
        source.forEach {
            addSource(it, Observer<Any> { x ->
                val newLiveData = switchMapFunction.apply(x)
                if (oldSource === newLiveData) {
                    return@Observer
                }
                oldSource?.let { it ->
                    removeSource(it)
                }
                oldSource = newLiveData
                oldSource?.let { it ->
                    addSource(it) { y ->
                        setValue(y)
                    }
                }
            })
        }
    }

    override fun setValue(value: T?) {
        if (value != getValue()) {
            super.setValue(value)
        }
    }
}
