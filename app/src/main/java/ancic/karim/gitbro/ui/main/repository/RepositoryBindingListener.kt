package ancic.karim.gitbro.ui.main.repository

import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.databinding.BindingListener
import ancic.karim.gitbro.databinding.DataBindingRecyclerViewAdapter
import ancic.karim.gitbro.databinding.ItemRepositoryBinding

class RepositoryBindingListener : BindingListener<ItemRepositoryBinding, Repository> {
    var onOwnerAvatarClick: DataBindingRecyclerViewAdapter.OnItemClickListeners<ItemRepositoryBinding, Repository>? = null

    override fun onBindItem(holder: DataBindingRecyclerViewAdapter.BindingHolder<ItemRepositoryBinding>, item: Repository, position: Int) {
        holder.binding.repositoryOwnerAvatar.setOnClickListener {
            onOwnerAvatarClick?.onItemClick(holder.binding, item, position)
        }
    }
}
