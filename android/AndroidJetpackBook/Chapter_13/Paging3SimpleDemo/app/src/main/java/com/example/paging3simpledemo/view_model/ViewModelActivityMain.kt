package com.example.paging3simpledemo.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paging3simpledemo.repository.UserDataPagingSource

class ViewModelActivityMain : ViewModel() {

    fun getUserData() = Pager(PagingConfig(pageSize = 3)) {
        UserDataPagingSource()
    }.flow

}