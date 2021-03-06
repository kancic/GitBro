package ancic.karim.gitbro.ui.user.details

import ancic.karim.gitbro.R
import ancic.karim.gitbro.api.response.RepositoryDetails
import ancic.karim.gitbro.api.response.User
import ancic.karim.gitbro.architecture.openInBrowser
import ancic.karim.gitbro.ui.base.BaseFragment
import android.os.Bundle
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding

class UserDetailsFragment : BaseFragment<ViewDataBinding, UserDetailsViewModel>() {
    companion object {
        private const val KEY_USER = "user"

        fun toBundle(user: User) = bundleOf(KEY_USER to user)
    }

    override fun provideViewResourceId() = R.layout.fragment_user_details
    override fun provideViewModelClass() = UserDetailsViewModel::class.java
    override fun provideOptionsMenuResourceId() = R.menu.menu_user_details

    override fun onParseArguments(arguments: Bundle) {
        super.onParseArguments(arguments)
        if (viewModel.user.value == null) {
            viewModel.user.value = arguments.getParcelable<User>(KEY_USER)?.apply {
                title = login
                viewModel.userName.value = login
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_open_in_browser -> viewModel.user.value?.htmlUrl?.openInBrowser(requireContext())
        }
        return super.onOptionsItemSelected(item)
    }
}