package ancic.karim.gitbro.ui.base

import ancic.karim.gitbro.api.NetworkRequest
import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

open class BaseRepository {

    private val mediatorArray = SparseArray<MediatorLiveData<*>>()

    protected fun <DISPLAY_RESPONSE, API_RESPONSE> executeNetworkRequest(networkRequest: NetworkRequest<DISPLAY_RESPONSE, API_RESPONSE>): LiveData<DISPLAY_RESPONSE?> {
        val mediatorLiveData = getMediator<DISPLAY_RESPONSE?>(networkRequest.getTag().hashCode())
        val networkCall = networkRequest.getNetworkCall()
        networkCall.enqueue(object : Callback<API_RESPONSE> {
            override fun onResponse(call: Call<API_RESPONSE>, response: Response<API_RESPONSE>) {
                if (response.isSuccessful && response.body() != null) {
                    mediatorLiveData.value = networkRequest.getResponseFromNetwork(response.body())
                }
            }

            override fun onFailure(call: Call<API_RESPONSE>, t: Throwable) {

            }
        })
        return mediatorLiveData
    }

    private fun <DISPLAY_RESPONSE> getMediator(key: Int): MediatorLiveData<DISPLAY_RESPONSE?> {
        var mediator: MediatorLiveData<DISPLAY_RESPONSE?>? = null
        try {
            mediator = mediatorArray.get(key) as MediatorLiveData<DISPLAY_RESPONSE?>
        } catch (e: Exception) {
            Timber.i(e)
        }

        if (mediator == null) {
            mediator = MediatorLiveData()
            mediatorArray.put(key, mediator)
        }
        return mediator
    }
}
