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
import io.drdroid.tipcalculator.databinding.FragmentCategoriesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesFragment : BaseFragment() {

    lateinit var bind: FragmentCategoriesBinding
    var genre: AnimeGenre =
        AnimeGenre(listOf())

    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: GenreAdapter
    lateinit var manager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            genre = io.drdroid.tipcalculator.data.remote.ApiDetails.apiClient.getGenres()
//            adapter.notifyDataSetChanged()
            adapter = io.drdroid.tipcalculator.adapters.GenreAdapter(
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
        bind = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
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