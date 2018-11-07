package ancic.karim.gitbro.ui.main

import ancic.karim.gitbro.R
import ancic.karim.gitbro.databinding.ActivityMainBinding
import ancic.karim.gitbro.databinding.item.OnItemClickListener
import ancic.karim.gitbro.ui.base.BaseActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun provideViewResourceId() = R.layout.activity_main
    override fun provideViewModelClass() = MainViewModel::class.java
    override fun provideToolbar() = binding.toolbar
    override fun provideNavigationHostFragmentId() = R.id.navigation_host
}
