package com.example.pagginatedlazycolumncompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class EmpViewModel : ViewModel() {
    val user: Flow<PagingData<User>> = Pager(PagingConfig(pageSize = 6)) {
        DataSource()
    }.flow.cachedIn(viewModelScope)
}