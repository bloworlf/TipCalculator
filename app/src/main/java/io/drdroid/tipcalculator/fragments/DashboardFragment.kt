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
import dagger.hilt.android.AndroidEntryPoint
import io.drdroid.tipcalculator.base.BaseFragment
import io.drdroid.tipcalculator.data.Anime
import io.drdroid.tipcalculator.data.remote.ApiCall
import io.drdroid.tipcalculator.databinding.FragmentDashboardBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : BaseFragment() {

    @Inject
    lateinit var apiCall: ApiCall

    private val id: Int = DashboardFragment::class.java.hashCode()

    lateinit var bind: FragmentDashboardBinding
    private lateinit var anime: Anime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        super.restoreRootView(id)?.let {
            bind = super.restoreRootView(id) as FragmentDashboardBinding
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!this::bind.isInitialized) {
            bind = FragmentDashboardBinding.inflate(inflater, container, false)

            super.storeRootView(id, bind)
        }
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

//        displayRandomAnime(imageView, title)
    }

    private fun displayRandomAnime(imageView: ImageView, title: TextView) {
        CoroutineScope(Dispatchers.Main).launch {
            anime = apiCall.getRandomAnime()

            title.text = anime.data?.title

            Glide.with(this@DashboardFragment)
                .load(Uri.parse(anime.data?.images?.jpg?.imageUrl))
                .into(imageView)
        }
    }
}