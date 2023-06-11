package com.capstone.nempatin.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.capstone.nempatin.R
import com.capstone.nempatin.ui.adapters.PropertyAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class HomeFragment : Fragment() {
    private lateinit var propertyAdapter: PropertyAdapter
    private val viewModel: PropertyViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val toolbar: Toolbar = view.findViewById(R.id.my_toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_newproperty)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        propertyAdapter = PropertyAdapter()

        recyclerView.adapter = propertyAdapter
        val snapHelper = LinearSnapHelper() // Or PagerSnapHelper for pager-like snapping
        snapHelper.attachToRecyclerView(recyclerView)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                propertyAdapter.submitData(pagingData)
            }
        }

        val searchView = view.findViewById<SearchView>(R.id.search_bar)
        val frameLayout = view.findViewById<FrameLayout>(R.id.search_bar_frame)

        // Expand SearchView click area
        frameLayout.setOnClickListener {
            searchView.isIconified = false // Open up the SearchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Perform your search operation here
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // React to text changes here
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
