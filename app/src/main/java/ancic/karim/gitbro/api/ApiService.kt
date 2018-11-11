package ancic.karim.gitbro.api

import ancic.karim.gitbro.api.response.ReadmeFileResponse
import ancic.karim.gitbro.api.response.SearchRepositoriesResponse
import ancic.karim.gitbro.api.response.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/search/repositories")
    fun searchRepositories(@Query("q") searchText: String?, @Query("sort") sortText: String?): Call<SearchRepositoriesResponse>

    @GET("/repos/{username}/{repositoryName}/readme")
    fun getReadmeFile(@Path("username") username: String, @Path("repositoryName") repositoryName: String): Call<ReadmeFileResponse>

    @GET("/users/{username}")
    fun getUserDetails(@Path("username") username: String): Call<User>
}