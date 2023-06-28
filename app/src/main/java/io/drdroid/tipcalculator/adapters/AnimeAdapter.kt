package io.drdroid.tipcalculator.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.drdroid.tipcalculator.R
import io.drdroid.tipcalculator.data.models.AnimeModel
import io.drdroid.tipcalculator.holders.AnimeHolder
import io.drdroid.tipcalculator.holders.GenreHolder

class AnimeAdapter(val context: Context, var data: List<AnimeModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.model_anime, parent, false)
        return AnimeHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val anime = data[position]
        val animeHolder: AnimeHolder = holder as AnimeHolder

        Glide.with(context)
            .load(Uri.parse(anime.images?.jpg?.imageUrl))
            .into(animeHolder.image)
        animeHolder.title.text = anime.title
    }

}
