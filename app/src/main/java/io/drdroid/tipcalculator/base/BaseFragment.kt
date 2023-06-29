package io.drdroid.tipcalculator.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private lateinit var bindings: MutableMap<Int, Any>

    fun storeRootView(id: Int, binding: Any) {
        if (id in bindings) {
            bindings.replace(id, binding)
        } else {
            bindings[id] = binding
        }
    }

    fun restoreRootView(id: Int): Any? {
        return bindings[id]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindings = mutableMapOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}