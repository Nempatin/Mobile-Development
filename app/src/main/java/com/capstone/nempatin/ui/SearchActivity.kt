package com.capstone.nempatin.ui


import com.capstone.nempatin.R
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Assuming the id of your toolbar is my_toolbar
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Assuming the id of your SearchView is search_bar
        val searchBar: SearchView = findViewById(R.id.search_bar)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Perform your search here
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // You can update the search results in real-time here
                return true
            }
        })

        // Fetch the query from the intent extra
        val query = intent.getStringExtra("query")

        // Set the query to the SearchView and expand it
        searchBar.setQuery(query, false)
        searchBar.setIconifiedByDefault(false)
        searchBar.requestFocus()

        // Assuming you have a RecyclerView for the search results
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_search_results)

        // Perform the initial search
        performSearch(query)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()  // This handles the back button press.
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun performSearch(query: String?) {
        // This method is where you'll actually perform the search and update your RecyclerView.
        // It's currently empty because the implementation will depend on your data source.

        //TODO
    }
}
