package ancic.karim.gitbro.ui.main

import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.architecture.LiveField
import ancic.karim.gitbro.ui.base.BaseRouter
import ancic.karim.gitbro.ui.base.BaseViewModel
import android.app.Application
import androidx.arch.core.util.Function
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : BaseViewModel<MainRepository, BaseRouter>(application, MainRepository(), BaseRouter()) {
    val repositoryVariableId = ObservableField<Int?>(BR.repository)
    val searchText = MutableLiveData<String?>()
    val sortText = MutableLiveData<String?>()
    val repositoryList = LiveField<List<Repository>>(searchText, sortText, switchMapFunction = Function {
        repository.getRepositoryList(searchText.value, sortText.value)
    })
}