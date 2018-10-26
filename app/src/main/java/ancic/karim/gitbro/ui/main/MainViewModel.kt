package ancic.karim.gitbro.ui.main

import ancic.karim.gitbro.architecture.LiveField
import ancic.karim.gitbro.architecture.map
import ancic.karim.gitbro.ui.base.BaseRepository
import ancic.karim.gitbro.ui.base.BaseRouter
import ancic.karim.gitbro.ui.base.BaseViewModel
import android.app.Application

class MainViewModel(application: Application) : BaseViewModel<BaseRepository, BaseRouter>(application, MainRepository(), BaseRouter()) {
    val searchText = LiveField<String>()
    val repositoryList = searchText.map { searchText -> repository.getRepositoryList(searchText) }
}