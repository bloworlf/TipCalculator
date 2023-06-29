package io.drdroid.tipcalculator.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    companion object Foo{
        private var bindings: MutableMap<Int, Any> = mutableMapOf()
//        private var dataSet: MutableMap<Int, List<Any>> = mutableMapOf()
    }

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

//    fun storeData(id: Int, data: List<Any>) {
//        dataSet[id] = data
//    }

//    fun restoreData(id: Int): List<Any>? {
//        return dataSet[id]
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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