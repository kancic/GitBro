package ancic.karim.gitbro.ui.main.repository

import ancic.karim.gitbro.api.response.Repository
import ancic.karim.gitbro.databinding.BindingListener
import ancic.karim.gitbro.databinding.DataBindingRecyclerViewAdapter
import ancic.karim.gitbro.databinding.ItemRepositoryBinding
import ancic.karim.gitbro.databinding.item.OnItemClickListener

class RepositoryBindingListener : BindingListener<ItemRepositoryBinding, Repository> {
    var onOwnerAvatarClick: OnItemClickListener<ItemRepositoryBinding, Repository>? = null

    override fun onBindItem(holder: DataBindingRecyclerViewAdapter.BindingHolder<ItemRepositoryBinding>, item: Repository, position: Int) {
        holder.binding.repositoryOwnerAvatar.setOnClickListener {
            onOwnerAvatarClick?.onItemClick(holder.binding, item, position)
        }
    }
}
