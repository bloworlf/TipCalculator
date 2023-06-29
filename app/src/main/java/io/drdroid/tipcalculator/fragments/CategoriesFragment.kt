package io.drdroid.tipcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.drdroid.tipcalculator.adapters.GenreAdapter
import io.drdroid.tipcalculator.base.BaseFragment
import io.drdroid.tipcalculator.data.AnimeGenre
import io.drdroid.tipcalculator.data.remote.ApiDetails
import io.drdroid.tipcalculator.databinding.FragmentCategoriesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesFragment : BaseFragment() {

    private val id: Int = CategoriesFragment::class.java.hashCode()

    lateinit var bind: FragmentCategoriesBinding
    var genre: AnimeGenre =
        AnimeGenre(listOf())

    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: GenreAdapter
    lateinit var manager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        super.restoreRootView(id)?.let {
            bind = super.restoreRootView(id) as FragmentCategoriesBinding
        }

        CoroutineScope(Dispatchers.Main).launch {
            genre = ApiDetails.apiClient.getGenres()
//            adapter.notifyDataSetChanged()
            adapter = GenreAdapter(
                this@CategoriesFragment.requireContext(),
                genre.data
            )
            recyclerView.adapter = adapter
//            genre.data?.forEach {
//                println(it?.url)
//            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!this::bind.isInitialized) {
            bind = FragmentCategoriesBinding.inflate(inflater, container, false)

            super.storeRootView(id, bind)
        }
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = bind.recyclerView
        manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        manager.orientation = GridLayoutManager.VERTICAL
//        adapter = GenreAdapter(this@CategoriesFragment.requireContext(), genre.data)
//        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager

    }
}