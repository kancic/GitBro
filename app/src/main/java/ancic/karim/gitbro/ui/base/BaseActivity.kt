package ancic.karim.gitbro.ui.base

import ancic.karim.gitbro.BR
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders

@SuppressLint("Registered")
abstract class BaseActivity<BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel<*, *>> : AppCompatActivity() {
    protected val activity: AppCompatActivity by lazy { this }
    protected lateinit var binding: BINDING
    protected lateinit var viewModel: VIEW_MODEL

    protected abstract fun provideViewResourceId(): Int
    protected abstract fun provideViewModelClass(): Class<VIEW_MODEL>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(activity).get(provideViewModelClass())

        if (provideViewResourceId() != 0) {
            binding = DataBindingUtil.setContentView(activity, provideViewResourceId())
            binding.setVariable(BR.viewModel, viewModel)
            binding.setLifecycleOwner(activity)
        }
    }
}
