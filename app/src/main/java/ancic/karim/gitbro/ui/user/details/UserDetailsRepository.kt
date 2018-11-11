package ancic.karim.gitbro.ui.user.details

import ancic.karim.gitbro.api.ApiManager
import ancic.karim.gitbro.api.NetworkRequest
import ancic.karim.gitbro.api.response.User
import ancic.karim.gitbro.ui.base.BaseRepository
import androidx.lifecycle.LiveData
import retrofit2.Call

class UserDetailsRepository : BaseRepository() {
    fun getUserDetails(userName: String): LiveData<User?> {
        return executeNetworkRequest(object : NetworkRequest<User, User> {
            override fun getNetworkCall(): Call<User> {
                return ApiManager.apiService.getUserDetails(userName)
            }

            override fun getResponseFromNetwork(data: User?): User? {
                return data
            }
        })
    }
}
