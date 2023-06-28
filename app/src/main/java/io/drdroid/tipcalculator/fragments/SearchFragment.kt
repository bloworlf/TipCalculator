package io.drdroid.tipcalculator.fragments

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.drdroid.tipcalculator.adapters.AnimeAdapter
import io.drdroid.tipcalculator.base.BaseFragment
import io.drdroid.tipcalculator.data.AnimeSearch
import io.drdroid.tipcalculator.data.models.AnimeModel
import io.drdroid.tipcalculator.databinding.FragmentSearchBinding
import jp.wasabeef.recyclerview.animators.LandingAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : BaseFragment() {
    lateinit var bind: FragmentSearchBinding
    lateinit var search: AnimeSearch
    private var animeList: MutableList<AnimeModel> = mutableListOf()
    lateinit var manager: StaggeredGridLayoutManager
    lateinit var adapter: AnimeAdapter
    private lateinit var recyclerView: RecyclerView

    private var page: Int = 1
    private var isLoading: Boolean = false
    private var hasMore: Boolean = true
    private var q: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSearchBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edSearch: EditText = bind.search
        val switch: SwitchCompat = bind.sfw
        switch.setOnCheckedChangeListener { compoundButton, checked ->
            adapter.filter.filter(checked.toString())
        }

        recyclerView = bind.recyclerView
        manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        manager = GridLayoutManager(this@SearchFragment.requireContext(), 2)
        recyclerView.layoutManager = manager
        adapter = AnimeAdapter(this@SearchFragment.requireContext(), animeList)
        recyclerView.adapter = adapter
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val visibleItemCount = manager.childCount
//                val pastVisibleItem = manager.findFirstCompletelyVisibleItemPosition()
//                val total = adapter.itemCount
//
//
//                if (!isLoading && hasMore) {
//
//                    if ((visibleItemCount + pastVisibleItem) >= total) {
//                        isLoading = true
//                        page++
//                        searchAnime(
//                            q = q,
//                            page = page,
//                            sfw = if (switch.isChecked) {
//                                true
//                            } else {
//                                null
//                            }
//                        )
//                    }
//                }
////                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = manager.childCount
                val firstVisibleItems: IntArray = IntArray(manager.spanCount)
                val firstVisibleItem =
                    manager.findFirstCompletelyVisibleItemPositions(firstVisibleItems)
                val totalItemCount = adapter.itemCount

                var pastVisibleItems = firstVisibleItems[0]


                if (!isLoading && hasMore) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
//                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        isLoading = true
                        page++
                        searchAnime(
                            q = q,
                            page = page,
                            sfw = if (switch.isChecked) {
                                true
                            } else {
                                null
                            }
                        )
                    }
                }
//                super.onScrolled(recyclerView, dx, dy)
            }
        })

        recyclerView.itemAnimator = LandingAnimator()

        edSearch.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || event.action == KeyEvent.ACTION_DOWN
                && event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                q = textView.text.toString()
                page = 1

                searchAnime(
                    q = q,
                    page = page,
                    sfw = if (switch.isChecked) {
                        true
                    } else {
                        null
                    }
                )
                textView.hideKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun searchAnime(q: String, page: Int, sfw: Boolean?) {
        CoroutineScope(Dispatchers.Main).launch {
            search = io.drdroid.tipcalculator.data.remote.ApiDetails.apiClient.searchAnime(
                q = q,
                page = page
            )

            if (page == 1 && animeList.size > 0) {
//                adapter.notifyItemRangeRemoved(0, animeList.size - 1)
                animeList.clear()
                adapter.notifyDataSetChanged()
            }

            isLoading = false
            hasMore = search.pagination.hasNextPage

            animeList.addAll(search.data)

            populateRecyclerView(search.data.size, page)
        }
    }

    private fun populateRecyclerView(size: Int, page: Int) {
        adapter.notifyItemRangeInserted(animeList.size - size, size)
        if (page == 1) {
            recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}