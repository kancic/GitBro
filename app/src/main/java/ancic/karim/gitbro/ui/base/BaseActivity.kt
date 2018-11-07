package ancic.karim.gitbro.ui.base

import ancic.karim.gitbro.BR
import ancic.karim.gitbro.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

@SuppressLint("Registered")
abstract class BaseActivity<BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel<*, *>> : AppCompatActivity() {
    protected val activity: AppCompatActivity by lazy { this }
    protected lateinit var binding: BINDING
    protected lateinit var viewModel: VIEW_MODEL
    protected lateinit var navigationController: NavController

    protected abstract fun provideViewResourceId(): Int
    protected abstract fun provideViewModelClass(): Class<VIEW_MODEL>
    protected abstract fun provideToolbar(): Toolbar?
    protected open fun provideNavigationHostFragmentId() = 0
    protected open fun provideOptionsMenuResourceId() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(activity).get(provideViewModelClass())
        viewModel.router.observe(this)

        if (provideViewResourceId() != 0) {
            binding = DataBindingUtil.setContentView(activity, provideViewResourceId())
            binding.setVariable(BR.viewModel, viewModel)
            binding.setLifecycleOwner(activity)
        }

        provideToolbar()?.also { toolbar ->
            setSupportActionBar(toolbar)
        }

        if (provideNavigationHostFragmentId() != 0) {
            navigationController = findNavController(provideNavigationHostFragmentId())
            provideToolbar()?.apply {
                val appBarConfiguration = AppBarConfiguration(navigationController.graph)
                setupWithNavController(navigationController, appBarConfiguration)
            }
        }

        intent?.extras?.also { extras ->
            onParseIntentExtras(extras)
        }
    }

    protected open fun onParseIntentExtras(extras: Bundle) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        if (provideOptionsMenuResourceId() != 0) {
            val inflater = menuInflater
            inflater.inflate(provideOptionsMenuResourceId(), menu)
        }
        return true
    }
}
