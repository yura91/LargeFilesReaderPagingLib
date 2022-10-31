package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.data

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class FilesDataSource(
    private val context: Context
) : PagingSource<Int, String>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        return try {
            withContext(Dispatchers.IO) {
                val nextPageNumber = params.key ?: 0
                val str = LogFilesReader().readFiles(context, nextPageNumber, params.loadSize)
                val list = mutableListOf<String>()
                if (str != null) {
                    list.add(str)
                     LoadResult.Page(
                        list,
                        null,
                        nextPageNumber + 1
                    )
                } else {
                    LoadResult.Page(
                        data = list,
                        prevKey = null, // Only paging forward.
                        nextKey = null
                    )
                }
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}