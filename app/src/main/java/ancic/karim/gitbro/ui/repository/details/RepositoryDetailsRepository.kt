package ancic.karim.gitbro.ui.repository.details

import ancic.karim.gitbro.api.ApiManager
import ancic.karim.gitbro.api.NetworkRequest
import ancic.karim.gitbro.api.response.ReadmeFileResponse
import ancic.karim.gitbro.ui.base.BaseRepository
import android.util.Base64
import androidx.lifecycle.LiveData
import retrofit2.Call
import java.nio.charset.Charset

class RepositoryDetailsRepository : BaseRepository() {
    fun getReadMeContent(ownerName: String, repositoryName: String): LiveData<String?> {
        return executeNetworkRequest(object : NetworkRequest<String, ReadmeFileResponse> {
            override fun getNetworkCall(): Call<ReadmeFileResponse> {
                return ApiManager.apiService.getReadmeFile(ownerName, repositoryName)
            }

            override fun getResponseFromNetwork(data: ReadmeFileResponse?): String? {
                return data?.content?.let { encodedContent ->
                    String(Base64.decode(encodedContent, Base64.DEFAULT), Charset.forName("UTF-8"))
                }
            }
        })
    }
}
