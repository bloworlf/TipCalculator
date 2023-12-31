package io.drdroid.tipcalculator.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.drdroid.tipcalculator.R

class AnimeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var image: ImageView
    var title: TextView
    var seasons: TextView
    var episodes: TextView

    init {
        image = itemView.findViewById(R.id.image)
        title = itemView.findViewById(R.id.title)
        seasons = itemView.findViewById(R.id.seasons)
        episodes = itemView.findViewById(R.id.episodes)
    }

}
