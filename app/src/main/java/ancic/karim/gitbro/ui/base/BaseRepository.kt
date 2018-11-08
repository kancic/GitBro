package ancic.karim.gitbro.ui.base

import ancic.karim.gitbro.api.NetworkRequest
import android.os.Handler
import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

open class BaseRepository {

    private val resultList = SparseArray<MediatorLiveData<*>>()
    private val runnableList = SparseArray<Runnable>()
    private val networkHandler = Handler()

    protected fun <DISPLAY_RESPONSE, API_RESPONSE> executeNetworkRequest(networkRequest: NetworkRequest<DISPLAY_RESPONSE, API_RESPONSE>): LiveData<DISPLAY_RESPONSE?> {
        val key = networkRequest.getTag().hashCode()
        val networkResult = getNetworkResult<DISPLAY_RESPONSE?>(key)
        val networkCall = networkRequest.getNetworkCall()

        runnableList.get(key)?.also {
            networkHandler.removeCallbacks(it)
        }

        Runnable {
            networkCall.enqueue(object : Callback<API_RESPONSE> {
                override fun onResponse(call: Call<API_RESPONSE>, response: Response<API_RESPONSE>) {
                    networkResult.value = if (response.isSuccessful && response.body() != null) {
                        networkRequest.getResponseFromNetwork(response.body())
                    } else {
                        null
                    }
                }

                override fun onFailure(call: Call<API_RESPONSE>, t: Throwable) {
                }
            })
        }.also { it ->
            runnableList.put(key, it)
            networkHandler.postDelayed(it, networkRequest.getRequestDelayInMillis())
        }
        return networkResult
    }

    private fun <DISPLAY_RESPONSE> getNetworkResult(key: Int): MediatorLiveData<DISPLAY_RESPONSE?> {
        var mediator: MediatorLiveData<DISPLAY_RESPONSE?>? = null
        try {
            mediator = resultList.get(key) as MediatorLiveData<DISPLAY_RESPONSE?>
        } catch (e: Exception) {
            Timber.i(e)
        }

        if (mediator == null) {
            mediator = MediatorLiveData()
            resultList.put(key, mediator)
        }
        return mediator
    }
}
