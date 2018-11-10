package ancic.karim.gitbro.ui.repository.details

import ancic.karim.gitbro.R
import ancic.karim.gitbro.api.response.RepositoryDetails
import ancic.karim.gitbro.ui.base.BaseFragment
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding

class RepositoryDetailsFragment : BaseFragment<ViewDataBinding, RepositoryDetailsViewModel>() {
    companion object {
        private const val KEY_REPOSITORY_DETAILS = "repositoryDetails"

        fun toBundle(repositoryDetails: RepositoryDetails) = bundleOf(KEY_REPOSITORY_DETAILS to repositoryDetails)
    }

    override fun provideViewResourceId() = R.layout.fragment_repository_details
    override fun provideViewModelClass() = RepositoryDetailsViewModel::class.java

    override fun onParseArguments(arguments: Bundle) {
        super.onParseArguments(arguments)
        viewModel.repositoryDetails.value = (arguments.getSerializable(KEY_REPOSITORY_DETAILS) as RepositoryDetails?)?.apply {
            title = name
        }
    }
}
