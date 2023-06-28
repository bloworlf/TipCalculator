package io.drdroid.tipcalculator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.drdroid.tipcalculator.R
import io.drdroid.tipcalculator.holders.GenreHolder

class GenreAdapter(private val context: Context, var data: List<io.drdroid.tipcalculator.data.models.GenreModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.model_genre, parent, false)
        return GenreHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

//    override fun getItemId(position: Int): Long {
//        return data[position].malId.toLong()
//    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val genreModel = data[position]
        val genreHolder: GenreHolder = holder as GenreHolder

        genreHolder.title.text = genreModel.name
        genreHolder.url.text = genreModel.url
        genreHolder.count.text = genreModel.type
    }
}