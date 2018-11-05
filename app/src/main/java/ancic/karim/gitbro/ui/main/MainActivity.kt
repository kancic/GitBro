package ancic.karim.gitbro.ui.main

import ancic.karim.gitbro.R
import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.databinding.DataBindingRecyclerViewAdapter
import ancic.karim.gitbro.databinding.ItemRepositoryBinding
import ancic.karim.gitbro.ui.base.BaseActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.ViewDataBinding

class MainActivity : BaseActivity<ViewDataBinding, MainViewModel>() {
    override fun provideViewResourceId() = R.layout.activity_main
    override fun provideViewModelClass() = MainViewModel::class.java
    override fun provideOptionsMenuResourceId() = R.menu.menu_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.bindingListener.onOwnerAvatarClick = object : DataBindingRecyclerViewAdapter.OnItemClickListeners<ItemRepositoryBinding, Repository> {
            override fun onItemClick(binding: ItemRepositoryBinding, item: Repository, position: Int) {
            }
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
