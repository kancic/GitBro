package ancic.karim.gitbro.ui.user.details

import ancic.karim.gitbro.R
import ancic.karim.gitbro.ui.base.BaseFragment
import androidx.databinding.ViewDataBinding

class UserDetailsFragment : BaseFragment<ViewDataBinding, UserDetailsViewModel>() {
    override fun provideViewResourceId() = R.layout.fragment_user_details
    override fun provideViewModelClass() = UserDetailsViewModel::class.java
}
