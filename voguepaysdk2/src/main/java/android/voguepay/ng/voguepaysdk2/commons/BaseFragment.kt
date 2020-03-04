package android.voguepay.ng.voguepaysdk2.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.voguepay.ng.voguepaysdk2.view.AbstractViewModel
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<in BINDING : ViewDataBinding, out VIEWMODEL : AbstractViewModel> :
    Fragment() {

    abstract fun getViewModel(): VIEWMODEL

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int

    abstract fun getLayoutBinding(binding: BINDING)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: BINDING = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.apply {
            setVariable(getBindingVariable(), getViewModel())
            executePendingBindings()
            lifecycleOwner = this@BaseFragment
        }
        getLayoutBinding(binding)
        return binding.root
    }
}