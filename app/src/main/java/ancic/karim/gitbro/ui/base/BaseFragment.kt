package ancic.karim.gitbro.ui.base

import ancic.karim.gitbro.BR
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel<*, *>> : Fragment() {
    var title: String? = null

    protected lateinit var binding: BINDING
    protected lateinit var viewModel: VIEW_MODEL

    protected abstract fun provideViewResourceId(): Int
    protected abstract fun provideViewModelClass(): Class<VIEW_MODEL>
    protected open fun provideOptionsMenuResourceId() = 0
    open fun isViewModelShared() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (provideOptionsMenuResourceId() != 0) {
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = super.onCreateView(inflater, container, savedInstanceState)

        viewModel = if (isViewModelShared()) {
            ViewModelProviders.of(requireActivity()).get(provideViewModelClass())
        } else {
            ViewModelProviders.of(this).get(provideViewModelClass())
        }

        if (provideViewResourceId() != 0) {
            binding = DataBindingUtil.inflate(inflater, provideViewResourceId(), container, false)
            binding.setVariable(BR.viewModel, viewModel)
            binding.setLifecycleOwner(activity)

            view = binding.root
        }

        arguments?.also { arguments ->
            onParseArguments(arguments)
        }

        return view
    }

    protected open fun onParseArguments(arguments: Bundle) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.router.observe(viewLifecycleOwner)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (provideOptionsMenuResourceId() != 0) {
            menu?.clear()
            inflater?.inflate(provideOptionsMenuResourceId(), menu)
        }
    }

    override fun onStart() {
        super.onStart()
        title?.let { title ->
            activity?.title = title
        }
    }
}
