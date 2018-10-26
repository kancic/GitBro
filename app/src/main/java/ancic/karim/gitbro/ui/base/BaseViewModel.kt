package ancic.karim.gitbro.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel<REPOSITORY : BaseRepository, ROUTER : BaseRouter>(application: Application, val repository: REPOSITORY, val router: ROUTER) :
        AndroidViewModel(application) {

}
