package ancic.karim.gitbro.ui.repository.search

import ancic.karim.gitbro.R
import ancic.karim.gitbro.databinding.item.OnItemClickListener
import ancic.karim.gitbro.ui.base.BaseFragment
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
        viewModel.bindingListener.onOwnerAvatarClick = OnItemClickListener { binding, item, _ ->
            findNavController().navigate(R.id.action_repositorySearchFragment_to_userDetailsFragment)
        }
        viewModel.bindingListener.onItemClick = OnItemClickListener { binding, item, _ ->
            findNavController().navigate(R.id.action_repositorySearchFragment_to_repositoryDetailsFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        when (viewModel.sortText.value) {
            null -> menu?.findItem(R.id.action_sort_default)?.isChecked = true
            "stars" -> menu?.findItem(R.id.action_sort_by_stars)?.isChecked = true
            "forks" -> menu?.findItem(R.id.action_sort_by_forks)?.isChecked = true
            "updated" -> menu?.findItem(R.id.action_sort_by_updated)?.isChecked = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_sort_default -> {
                viewModel.sortText.value = null
                item.isChecked = true
                return true
            }
            R.id.action_sort_by_stars -> {
                viewModel.sortText.value = "stars"
                item.isChecked = true
                return true
            }
            R.id.action_sort_by_forks -> {
                viewModel.sortText.value = "forks"
                item.isChecked = true
                return true
            }
            R.id.action_sort_by_updated -> {
                viewModel.sortText.value = "updated"
                item.isChecked = true
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
