package ancic.karim.gitbro.ui.repository.search

import ancic.karim.gitbro.api.response.RepositoryDetails
import ancic.karim.gitbro.architecture.LiveField
import ancic.karim.gitbro.ui.base.BaseRouter
import ancic.karim.gitbro.ui.base.BaseViewModel
import android.app.Application
import androidx.arch.core.util.Function
import androidx.databinding.library.baseAdapters.BR

class RepositorySearchViewModel(application: Application) : BaseViewModel<RepositorySearchRepository, BaseRouter>(
    application,
    RepositorySearchRepository(), BaseRouter()
) {
    val itemVariableId = LiveField<Int?>(BR.repositoryDetails)
    val itemBindingListener = RepositorySearchBindingListener()
    val searchText = LiveField<String?>()
    val repositorySort = LiveField<RepositorySort?>(RepositorySort.DEFAULT)
    val repositoryList = LiveField<List<RepositoryDetails>>(searchText, repositorySort, switchMapFunction = Function {
        repository.getRepositoryList(searchText.value, repositorySort.value)
    })
}

enum class RepositorySort {
    DEFAULT, STARS, FORKS, UPDATED
}
