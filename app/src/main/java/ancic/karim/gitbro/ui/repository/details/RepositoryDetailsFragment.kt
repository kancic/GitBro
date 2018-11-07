package ancic.karim.gitbro.ui.repository.details

import ancic.karim.gitbro.R
import ancic.karim.gitbro.ui.base.BaseFragment
import androidx.databinding.ViewDataBinding

class RepositoryDetailsFragment : BaseFragment<ViewDataBinding, RepositoryDetailsViewModel>() {
    override fun provideViewResourceId() = R.layout.fragment_repository_details
    override fun provideViewModelClass() = RepositoryDetailsViewModel::class.java
}
