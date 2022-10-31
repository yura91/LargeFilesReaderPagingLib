package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.data.FilesDataSource

class FilesLoadViewModel(context: Context) : ViewModel() {
    var newsList: LiveData<PagingData<String>>
    private val pageSize = 1024

    init {
        newsList = Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
                initialLoadSize = pageSize
            ),
            pagingSourceFactory = {
                FilesDataSource(context)
            }
        ).liveData
    }
}