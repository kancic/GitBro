package ancic.karim.gitbro.architecture

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.format.DateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.text.SimpleDateFormat
import java.util.*

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

fun String.formatDate(context: Context): String {
    val apiDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    val date = apiDateFormat.parse(this)
    val deviceDateFormat = DateFormat.getLongDateFormat(context)
    return deviceDateFormat.format(date)
}

fun String.openInBrowser(context: Context) {
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(this)))
}