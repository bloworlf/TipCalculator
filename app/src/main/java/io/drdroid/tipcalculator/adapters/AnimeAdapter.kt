package io.drdroid.tipcalculator.adapters

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import io.drdroid.tipcalculator.R
import io.drdroid.tipcalculator.activities.Anime
import io.drdroid.tipcalculator.data.models.AnimeModel
import io.drdroid.tipcalculator.holders.AnimeHolder
import java.util.Objects


class AnimeAdapter(val context: Context, var data: List<AnimeModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>()/*, Filterable*/ {

//    var filteredData: List<AnimeModel> = data

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
//        animeHolder.seasons.text = StringUtils.abbreviate(anime.duration, 6)
        animeHolder.seasons.text = anime.duration
        animeHolder.episodes.text = anime.episodes.toString()

        animeHolder.itemView.setOnClickListener {
            openUrl(anime.url)
//            var intent = Intent(context, Anime::class.java)
//            intent.putExtra("anime", Gson().toJson(anime))
//            context.startActivity(intent)
        }
    }

    private fun openUrl(url: String?) {
        if (getCustomTabsPackages(context, Uri.parse(url)).size > 0) {
            val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
            builder.setShowTitle(true)
            val customTabsIntent: CustomTabsIntent = builder.build()
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://"))
            val resolveInfo = context.packageManager.resolveActivity(
                browserIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
            if (resolveInfo != null && resolveInfo.activityInfo.packageName.isNotEmpty()) {
                customTabsIntent.intent.setPackage(resolveInfo.activityInfo.packageName)
            }
            url?.let {
                customTabsIntent.launchUrl(context, Uri.parse(url))
            }

        } else {
            Toast.makeText(
                context,
                "Couldn't find an app to open the web page.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun getCustomTabsPackages(context: Context, url: Uri?): MutableList<ResolveInfo> {
        val pm = context.packageManager
        // Get default VIEW intent handler.
        val activityIntent = Intent(Intent.ACTION_VIEW, url)

        // Get all apps that can handle VIEW intents.
        val resolvedActivityList = pm.queryIntentActivities(activityIntent, 0)
        val packagesSupportingCustomTabs: MutableList<ResolveInfo> = mutableListOf()
        for (info in resolvedActivityList) {
            val serviceIntent = Intent()
            serviceIntent.action = ACTION_CUSTOM_TABS_CONNECTION
            serviceIntent.setPackage(info.activityInfo.packageName)
            // Check if this package also resolves the Custom Tabs service.
            if (pm.resolveService(serviceIntent, 0) != null) {
                packagesSupportingCustomTabs.add(info)
            }
        }
        return packagesSupportingCustomTabs
    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val filterResults = FilterResults()
//                if (constraint.isNullOrEmpty()) {
//                    filterResults.count = data.size
//                    filterResults.values = data
//                } else {
//                    val resultList: MutableList<AnimeModel> = mutableListOf()
//                    val sfw: Boolean = constraint.toString().toBoolean()
//                    for (d in data) {
//                        val rating: Rating = Rating.access(d.rating)
//                        if (sfw && rating.level == Safety.SFW) {
//                            resultList.add(d)
//                        }
//                        filterResults.count = resultList.size
//                        filterResults.values = resultList
//                    }
//                }
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
//                filteredData = results.values as MutableList<AnimeModel>
//                notifyDataSetChanged()
//            }
//        }
//    }
}
