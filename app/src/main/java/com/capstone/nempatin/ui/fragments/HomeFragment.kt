package com.capstone.nempatin.ui.fragments

import NearbyAdapter
import android.view.View
import androidx.core.util.Pair
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.nempatin.R
import com.capstone.nempatin.data.PropertyRepository
import com.capstone.nempatin.data.network.ApiConfig
import com.capstone.nempatin.domain.Property
import com.capstone.nempatin.ui.SearchActivity
import com.capstone.nempatin.ui.adapters.LatestAddedAdapter
import com.capstone.nempatin.ui.custom.ScaleCenterItemDecoration
import com.capstone.nempatin.ui.custom.ScaleCenterSnapHelper
import com.capstone.nempatin.ui.profile.ProfileActivity
import com.capstone.nempatin.utils.LocationUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var nearbyAdapter: NearbyAdapter
    private lateinit var latestAddedAdapter: LatestAddedAdapter
    lateinit var viewModel: PropertyViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.my_toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = ApiConfig.getApiService()
        val propertyRepository = PropertyRepository(apiService)
        viewModel = ViewModelProvider(this, PropertyViewModelFactory(propertyRepository)).get(PropertyViewModel::class.java)

        val nearbyRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_nearby)
        nearbyRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        nearbyAdapter = NearbyAdapter()
        nearbyRecyclerView.adapter = nearbyAdapter

        val latestAddedRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_latestadded)
        latestAddedRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        latestAddedAdapter = LatestAddedAdapter()
        latestAddedRecyclerView.adapter = latestAddedAdapter

        val snapHelper2 = ScaleCenterSnapHelper()

        snapHelper2.attachToRecyclerView(latestAddedRecyclerView)


        // Here you observe the properties LiveData and update the adapters when the data changes
        lifecycleScope.launch {
            viewModel.pagedProperties.collect { pagingData: PagingData<Property> ->
                nearbyAdapter.submitData(pagingData)
                latestAddedAdapter.submitData(pagingData)
            }
        }
        val profileButton: ImageButton = view.findViewById(R.id.profile_button)
        profileButton.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
        }

        val searchView = view.findViewById<SearchView>(R.id.search_bar)
        val frameLayout = view.findViewById<FrameLayout>(R.id.search_bar_frame)

        frameLayout.setOnClickListener {
            searchView.isIconified = false
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val searchIntent = Intent(activity, SearchActivity::class.java)
                searchIntent.putExtra("query", query)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    requireActivity(),
                    Pair.create(searchView as View, "search_bar_transition")
                )

                ActivityCompat.startActivity(requireActivity(), searchIntent, options.toBundle())
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // React to text changes here
                return false
            }
        })

        if (checkLocationPermission()) {
            getCurrentLocation()
        } else {
            requestLocationPermission()
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                // Handle permission denied
            }
        }
    }

    private fun getCurrentLocation() {
        if (checkLocationPermission()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        setupObservers(latitude, longitude)
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle location retrieval failure
                }
        }
    }


    private fun setupObservers(latitude: Double, longitude: Double) {
        lifecycleScope.launch {
            viewModel.pagedProperties.collectLatest { pagingData ->
                // Distribute all properties to the latestAddedAdapter
                latestAddedAdapter.submitData(pagingData)

                // Filter nearby properties and set them to the nearbyAdapter
                val nearbyPagingData = pagingData.filter { property ->
                    LocationUtils.isNearby(latitude, longitude, property.latitude, property.longitude)
                }
                nearbyAdapter.submitData(nearbyPagingData)
            }
        }
    }




    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
}
