package com.capstone.nempatin.ui.fragments

import androidx.lifecycle.*
import com.capstone.nempatin.data.network.ApiService
import com.capstone.nempatin.domain.Property
import kotlinx.coroutines.launch

class PropertyViewModel(private val apiService: ApiService) : ViewModel() {

    private var offset: Int = 0
    private val _properties = MutableLiveData<List<Property>?>()
    val properties: MutableLiveData<List<Property>?> get() = _properties

    init {
        fetchProperties()
    }

    fun fetchProperties() {
        viewModelScope.launch {
            try {
                val response = apiService.getProperties(offset, 5)
                val properties = response.body()?.mapIndexed { index, propertyResponse ->
                    Property(
                        id = offset + index + 1,
                        name = propertyResponse.name,
                        city = propertyResponse.city,
                        latitude = propertyResponse.latitude,
                        longitude = propertyResponse.longitude,
                        price = propertyResponse.price,
                        buildingArea = propertyResponse.buildingArea,
                        landArea = propertyResponse.landArea,
                        bedrooms = propertyResponse.bedrooms,
                        bathrooms = propertyResponse.bathrooms,
                        garage = propertyResponse.garage,
                        certificate = propertyResponse.certificate,
                        phoneNumber = propertyResponse.phoneNumber,
                        timestamp = System.currentTimeMillis()
                    )
                }
                offset += 5
                _properties.value = properties
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}







