package ancic.karim.gitbro.ui.repository.search

import ancic.karim.gitbro.api.ApiManager
import ancic.karim.gitbro.api.NetworkRequest
import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.api.response.SearchRepositoriesResponse
import ancic.karim.gitbro.ui.base.BaseRepository
import androidx.lifecycle.LiveData
import retrofit2.Call

class RepositorySearchRepository : BaseRepository() {
    fun getRepositoryList(searchText: String?, repositorySort: RepositorySort?): LiveData<List<Repository>?> {
        val sortText = when (repositorySort) {
            null, RepositorySort.DEFAULT -> null
            RepositorySort.STARS -> "stars"
            RepositorySort.FORKS -> "forks"
            RepositorySort.UPDATED -> "updated"
        }
        return executeNetworkRequest(object : NetworkRequest<List<Repository>, SearchRepositoriesResponse> {
            override fun getNetworkCall(): Call<SearchRepositoriesResponse> {
                return ApiManager.apiService.searchRepositories(searchText, sortText)
            }

            override fun getResponseFromNetwork(data: SearchRepositoriesResponse?): List<Repository>? {
                return data?.repositoryList
            }

            override fun getRequestDelayInMillis(): Long {
                return 1000
            }
        })
    }
}
