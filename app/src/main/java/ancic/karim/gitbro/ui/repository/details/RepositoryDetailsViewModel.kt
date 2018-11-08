package ancic.karim.gitbro.ui.repository.details

import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.ui.base.BaseRepository
import ancic.karim.gitbro.ui.base.BaseRouter
import ancic.karim.gitbro.ui.base.BaseViewModel
import android.app.Application
import androidx.lifecycle.MutableLiveData

class RepositoryDetailsViewModel(application: Application) : BaseViewModel<BaseRepository, BaseRouter>(application, BaseRepository(), BaseRouter()) {
    val repositoryDetails = MutableLiveData<Repository?>()
}
