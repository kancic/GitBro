package ancic.karim.gitbro.ui.main

import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.architecture.LiveField
import ancic.karim.gitbro.ui.base.BaseRouter
import ancic.karim.gitbro.ui.base.BaseViewModel
import ancic.karim.gitbro.ui.main.repository.RepositoryBindingListener
import android.app.Application
import androidx.arch.core.util.Function
import androidx.databinding.library.baseAdapters.BR

class MainViewModel(application: Application) : BaseViewModel<MainRepository, BaseRouter>(application, MainRepository(), BaseRouter()) {
    val repositoryVariableId = LiveField<Int?>(BR.repository)
    val bindingListener = RepositoryBindingListener()
    val searchText = LiveField<String?>()
    val sortText = LiveField<String?>()
    val repositoryList = LiveField<List<Repository>>(searchText, sortText, switchMapFunction = Function {
        repository.getRepositoryList(searchText.value, sortText.value)
    })
}