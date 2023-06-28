package io.drdroid.tipcalculator.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.drdroid.tipcalculator.R

class GenreHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView
    var url: TextView
    var count: TextView

    init {
        title = itemView.findViewById(R.id.title)
        url = itemView.findViewById(R.id.url)
        count = itemView.findViewById(R.id.count)
    }
}
