package ancic.karim.gitbro.ui.repository.search

import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.databinding.BindingListener
import ancic.karim.gitbro.databinding.DataBindingRecyclerViewAdapter
import ancic.karim.gitbro.databinding.ItemRepositoryBinding
import ancic.karim.gitbro.databinding.item.OnItemClickListener

class RepositorySearchBindingListener : BindingListener<ItemRepositoryBinding, Repository> {
    var onOwnerAvatarClick: OnItemClickListener<ItemRepositoryBinding, Repository>? = null
    var onItemClick: OnItemClickListener<ItemRepositoryBinding, Repository>? = null

    override fun onBindItem(holder: DataBindingRecyclerViewAdapter.BindingHolder<ItemRepositoryBinding>, item: Repository, position: Int) {
        holder.binding.root.setOnClickListener {
            onItemClick?.onItemClick(holder.binding, item, position)
        }
        holder.binding.repositoryOwnerAvatar.setOnClickListener {
            onOwnerAvatarClick?.onItemClick(holder.binding, item, position)
        }
    }
}
