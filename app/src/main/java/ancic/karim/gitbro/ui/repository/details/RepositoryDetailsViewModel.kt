package ancic.karim.gitbro.ui.repository.details

import ancic.karim.gitbro.api.response.RepositoryDetails
import ancic.karim.gitbro.architecture.formatDate
import ancic.karim.gitbro.architecture.map
import ancic.karim.gitbro.architecture.nonNull
import ancic.karim.gitbro.architecture.switchMap
import ancic.karim.gitbro.ui.base.BaseRouter
import ancic.karim.gitbro.ui.base.BaseViewModel
import android.app.Application
import androidx.lifecycle.MutableLiveData

class RepositoryDetailsViewModel(application: Application) :
    BaseViewModel<RepositoryDetailsRepository, BaseRouter>(application, RepositoryDetailsRepository(), BaseRouter()) {
    val repositoryDetails = MutableLiveData<RepositoryDetails?>()
    val dateCreated = repositoryDetails.nonNull().map { repositoryDetails -> repositoryDetails.createdAt.formatDate(application) }
    val dateUpdated = repositoryDetails.nonNull().map { repositoryDetails -> repositoryDetails.updatedAt.formatDate(application) }
    val readMeContent = repositoryDetails.nonNull().switchMap { repositoryDetails ->
        repository.getReadMeContent(
            repositoryDetails.owner.login, repositoryDetails.name
        )
    }
}