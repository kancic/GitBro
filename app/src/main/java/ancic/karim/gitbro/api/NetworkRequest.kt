package ancic.karim.gitbro.api

import retrofit2.Call

interface NetworkRequest<DISPLAY_RESPONSE, API_RESPONSE> {
    fun getNetworkCall(): Call<API_RESPONSE>
    fun getResponseFromNetwork(data: API_RESPONSE?): DISPLAY_RESPONSE?

    fun getTag(): String {
        return getNetworkCall().request().url().toString()
    }
}
