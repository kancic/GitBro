package ancic.karim.gitbro.ui.main

import ancic.karim.gitbro.architecture.switchMap
import ancic.karim.gitbro.ui.base.BaseRouter
import ancic.karim.gitbro.ui.base.BaseViewModel
import android.app.Application
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : BaseViewModel<MainRepository, BaseRouter>(application, MainRepository(), BaseRouter()) {
    val repositoryVariableId = ObservableField<Int?>(BR.repository)
    val searchText = MutableLiveData<String>()
    val repositoryList = searchText.switchMap { searchText -> repository.getRepositoryList(searchText) }
}