package com.capstone.nempatin.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.nempatin.data.network.ApiService

class PropertyViewModelFactory(private val apiService: ApiService): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PropertyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PropertyViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}