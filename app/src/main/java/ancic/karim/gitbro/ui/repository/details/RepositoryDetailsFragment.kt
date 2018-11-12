package ancic.karim.gitbro.ui.repository.details

import ancic.karim.gitbro.R
import ancic.karim.gitbro.api.response.RepositoryDetails
import ancic.karim.gitbro.architecture.openInBrowser
import ancic.karim.gitbro.ui.base.BaseFragment
import android.os.Bundle
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding

class RepositoryDetailsFragment : BaseFragment<ViewDataBinding, RepositoryDetailsViewModel>() {
    companion object {
        private const val KEY_REPOSITORY_DETAILS = "repositoryDetails"

        fun toBundle(repositoryDetails: RepositoryDetails) = bundleOf(KEY_REPOSITORY_DETAILS to repositoryDetails)
    }

    override fun provideViewResourceId() = R.layout.fragment_repository_details
    override fun provideViewModelClass() = RepositoryDetailsViewModel::class.java
    override fun provideOptionsMenuResourceId() = R.menu.menu_repository_details

    override fun onParseArguments(arguments: Bundle) {
        super.onParseArguments(arguments)
        viewModel.repositoryDetails.value = arguments.getParcelable<RepositoryDetails>(KEY_REPOSITORY_DETAILS)?.apply {
            title = name
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_open_in_browser -> viewModel.repositoryDetails.value?.htmlUrl?.openInBrowser(requireContext())
        }
        return super.onOptionsItemSelected(item)
    }
}
