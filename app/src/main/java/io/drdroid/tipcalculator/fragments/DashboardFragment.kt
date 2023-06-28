package io.drdroid.tipcalculator.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import io.drdroid.tipcalculator.data.Anime
import io.drdroid.tipcalculator.data.remote.ApiDetails
import io.drdroid.tipcalculator.base.BaseFragment
import io.drdroid.tipcalculator.databinding.FragmentDashboardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : BaseFragment() {

    lateinit var bind: FragmentDashboardBinding
    lateinit var anime: io.drdroid.tipcalculator.data.Anime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentDashboardBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val randomBtn: AppCompatButton = bind.randomBtn
        val imageView: ImageView = bind.imageView
        val title: TextView = bind.title

        randomBtn.setOnClickListener {
            displayRandomAnime(imageView, title)
        }

        displayRandomAnime(imageView, title)
    }

    private fun displayRandomAnime(imageView: ImageView, title: TextView) {
        CoroutineScope(Dispatchers.Main).launch {
            anime = io.drdroid.tipcalculator.data.remote.ApiDetails.apiClient.getRandomAnime()

            title.text = anime.data?.title

            Glide.with(this@DashboardFragment)
                .load(Uri.parse(anime.data?.images?.jpg?.imageUrl))
                .into(imageView)
        }
    }
}