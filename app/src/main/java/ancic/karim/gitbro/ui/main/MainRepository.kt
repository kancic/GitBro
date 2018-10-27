package ancic.karim.gitbro.ui.main

import ancic.karim.gitbro.api.ApiManager
import ancic.karim.gitbro.api.NetworkRequest
import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.api.response.SearchRepositoriesResponse
import ancic.karim.gitbro.ui.base.BaseRepository
import androidx.lifecycle.LiveData
import retrofit2.Call

class MainRepository : BaseRepository() {
    fun getRepositoryList(searchText: String?): LiveData<List<Repository>?> {
        return executeNetworkRequest(object : NetworkRequest<List<Repository>, SearchRepositoriesResponse> {
            override fun getNetworkCall(): Call<SearchRepositoriesResponse> {
                return ApiManager.getInstance().service.searchRepositories(searchText)
            }

            override fun getResponseFromNetwork(data: SearchRepositoriesResponse?): List<Repository>? {
                return data?.repositoryList
            }
        })
    }
}
