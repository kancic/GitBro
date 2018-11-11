package ancic.karim.gitbro.ui.repository.search

import ancic.karim.gitbro.R
import ancic.karim.gitbro.databinding.item.OnItemClickListener
import ancic.karim.gitbro.ui.base.BaseFragment
import ancic.karim.gitbro.ui.user.details.UserDetailsFragment
import ancic.karim.gitbro.ui.repository.details.RepositoryDetailsFragment
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController

class RepositorySearchFragment : BaseFragment<ViewDataBinding, RepositorySearchViewModel>() {
    override fun provideViewResourceId() = R.layout.fragment_repository_search
    override fun provideViewModelClass() = RepositorySearchViewModel::class.java
    override fun provideOptionsMenuResourceId() = R.menu.menu_repository_search

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.itemBindingListener.onOwnerAvatarClick = OnItemClickListener { binding, item, _ ->
            findNavController().navigate(R.id.action_repositorySearchFragment_to_userDetailsFragment, UserDetailsFragment.toBundle(item.owner))
        }
        viewModel.itemBindingListener.onItemClick = OnItemClickListener { binding, item, _ ->
            findNavController().navigate(R.id.action_repositorySearchFragment_to_repositoryDetailsFragment, RepositoryDetailsFragment.toBundle(item))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        when (viewModel.repositorySort.value) {
            RepositorySort.DEFAULT -> menu?.findItem(R.id.action_sort_default)?.isChecked = true
            RepositorySort.STARS -> menu?.findItem(R.id.action_sort_by_stars)?.isChecked = true
            RepositorySort.FORKS -> menu?.findItem(R.id.action_sort_by_forks)?.isChecked = true
            RepositorySort.UPDATED -> menu?.findItem(R.id.action_sort_by_updated)?.isChecked = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_sort_default -> {
                viewModel.repositorySort.value = RepositorySort.DEFAULT
                item.isChecked = true
                return true
            }
            R.id.action_sort_by_stars -> {
                viewModel.repositorySort.value = RepositorySort.STARS
                item.isChecked = true
                return true
            }
            R.id.action_sort_by_forks -> {
                viewModel.repositorySort.value = RepositorySort.FORKS
                item.isChecked = true
                return true
            }
            R.id.action_sort_by_updated -> {
                viewModel.repositorySort.value = RepositorySort.UPDATED
                item.isChecked = true
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
