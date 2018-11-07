package ancic.karim.gitbro.ui.main

import ancic.karim.gitbro.R
import ancic.karim.gitbro.databinding.item.OnItemClickListener
import ancic.karim.gitbro.ui.base.BaseActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.ViewDataBinding

class MainActivity : BaseActivity<ViewDataBinding, MainViewModel>() {
    override fun provideViewResourceId() = R.layout.activity_main
    override fun provideViewModelClass() = MainViewModel::class.java
}
