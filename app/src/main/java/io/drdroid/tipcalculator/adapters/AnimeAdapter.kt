package io.drdroid.tipcalculator.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.drdroid.tipcalculator.R
import io.drdroid.tipcalculator.data.models.AnimeModel
import io.drdroid.tipcalculator.enums.Rating
import io.drdroid.tipcalculator.enums.Safety
import io.drdroid.tipcalculator.holders.AnimeHolder

class AnimeAdapter(val context: Context, var data: List<AnimeModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var filteredData: List<AnimeModel> = data

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint.isNullOrEmpty()) {
                    filterResults.count = data.size
                    filterResults.values = data
                } else {
                    val resultList: MutableList<AnimeModel> = mutableListOf()
                    val sfw: Boolean = constraint.toString().toBoolean()
                    for (d in data) {
                        val rating: Rating = Rating.access(d.rating)
                        if (sfw && rating.level == Safety.SFW) {
                            resultList.add(d)
                        }
                        filterResults.count = resultList.size
                        filterResults.values = resultList
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                filteredData = results.values as MutableList<AnimeModel>
                notifyDataSetChanged()
            }
        }
    }
}
