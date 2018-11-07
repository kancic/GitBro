package ancic.karim.gitbro.ui.main

import ancic.karim.gitbro.ui.base.BaseRepository
import ancic.karim.gitbro.ui.base.BaseRouter
import ancic.karim.gitbro.ui.base.BaseViewModel
import android.app.Application

class MainViewModel(application: Application) : BaseViewModel<BaseRepository, BaseRouter>(application, BaseRepository(), BaseRouter()
)