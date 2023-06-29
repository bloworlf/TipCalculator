package io.drdroid.tipcalculator.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.drdroid.tipcalculator.R

class RecyclerViewDataObserver(private val recyclerView: RecyclerView, val view: View) :
    RecyclerView.AdapterDataObserver() {

    init {
//        val textView: TextView = view.findViewById(R.id.empty)

        checkIfEmpty()
    }

    override fun onChanged() {
        super.onChanged()
        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        if (recyclerView.adapter != null) {
            val empty = recyclerView.adapter!!.itemCount == 0

            view.visibility = if (empty) View.VISIBLE else View.GONE
            recyclerView.visibility = if (empty) View.GONE else View.VISIBLE
        }
    }
}