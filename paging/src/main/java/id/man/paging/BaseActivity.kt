package id.man.paging

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 *
 * Created by Lukmanul Hakim on  1/14/2022
 * devs.lukman@gmail.com
 */
abstract class BaseActivity<VM : BaseListVM, VB : ViewBinding> : AppCompatActivity() {

    abstract val viewModel: VM

    protected var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        onViewReady()
    }

    open fun onViewReady() {
        initProcess()
        initSubscription()
        onActionListener()
    }

    protected abstract fun initProcess()

    protected abstract fun initSubscription()

    protected abstract fun onActionListener()
}