package com.capstone.nempatin.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.capstone.nempatin.data.PropertyPagingSource

class PropertyViewModel : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 20, enablePlaceholders = false)
    ) {
        PropertyPagingSource()
    }.flow.cachedIn(viewModelScope)
}
