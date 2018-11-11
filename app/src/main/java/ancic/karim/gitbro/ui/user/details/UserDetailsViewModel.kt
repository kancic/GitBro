package ancic.karim.gitbro.ui.user.details

import ancic.karim.gitbro.architecture.nonNull
import ancic.karim.gitbro.architecture.switchMap
import ancic.karim.gitbro.ui.base.BaseRouter
import ancic.karim.gitbro.ui.base.BaseViewModel
import android.app.Application
import androidx.lifecycle.MutableLiveData

class UserDetailsViewModel(application: Application) :
    BaseViewModel<UserDetailsRepository, BaseRouter>(application, UserDetailsRepository(), BaseRouter()) {
    val userName = MutableLiveData<String?>()
    val user = userName.nonNull().switchMap { userName -> repository.getUserDetails(userName) }
}
