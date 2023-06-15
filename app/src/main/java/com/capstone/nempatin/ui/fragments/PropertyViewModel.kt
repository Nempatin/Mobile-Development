package com.capstone.nempatin.ui.fragments

import androidx.lifecycle.*
import com.capstone.nempatin.data.network.ApiService
import com.capstone.nempatin.domain.Property
import kotlinx.coroutines.launch

class PropertyViewModel(private val apiService: ApiService) : ViewModel() {

    private var lastPropertyId: String? = null
    private val _properties = MutableLiveData<List<Property>>()
    val properties: LiveData<List<Property>> get() = _properties

    init {
        fetchProperties()
    }

    fun fetchProperties() {
        viewModelScope.launch {
            try {
                val response = apiService.getPropertiesAfterId(lastPropertyId, 5)
                if (response.isSuccessful) { // check if the response is successful
                    val propertiesList = response.body() // get the body of the response
                    if (!propertiesList.isNullOrEmpty()) { // check if the properties list is not empty
                        lastPropertyId = propertiesList.last().id
                        _properties.value = propertiesList
                    }
                } else {
                    // Handle the case when the API call is not successful
                }
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}




