package io.drdroid.tipcalculator.activities

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.gson.Gson
import io.drdroid.tipcalculator.base.BaseActivity
import io.drdroid.tipcalculator.data.models.AnimeModel
import io.drdroid.tipcalculator.databinding.ActivityAnimeBinding

class Anime : BaseActivity() {

    lateinit var bind: ActivityAnimeBinding
    lateinit var anime: AnimeModel

    lateinit var toolbar: Toolbar
    lateinit var title: TextView
    lateinit var titleEnglish: TextView
    lateinit var image: ImageView
    lateinit var synopsis: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(bind.root)

        anime = Gson().fromJson(intent.getStringExtra("anime"), AnimeModel::class.java)

        toolbar = bind.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            this@Anime.finish()
        }

        title = bind.title
        titleEnglish = bind.titleEnglish
        image = bind.image
        synopsis = bind.synopsis

        title.text = anime.title
        anime.titleEnglish?.let {
            titleEnglish.visibility = View.VISIBLE
            titleEnglish.text = "(${anime.titleEnglish})"
        }
        synopsis.text = "Synopsis: ${anime.synopsis}"

        Glide.with(this@Anime)
            .load(Uri.parse(anime.images?.jpg?.largeImageUrl))
            .into(image)
    }
}