package ancic.karim.gitbro.ui.repository.details

import ancic.karim.gitbro.R
import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.ui.base.BaseFragment
import android.os.Bundle
import androidx.databinding.ViewDataBinding

class RepositoryDetailsFragment : BaseFragment<ViewDataBinding, RepositoryDetailsViewModel>() {
    override fun provideViewResourceId() = R.layout.fragment_repository_details
    override fun provideViewModelClass() = RepositoryDetailsViewModel::class.java

    override fun onParseArguments(arguments: Bundle) {
        super.onParseArguments(arguments)
        viewModel.repositoryDetails.value = arguments.getSerializable("repository") as Repository?
    }
}
