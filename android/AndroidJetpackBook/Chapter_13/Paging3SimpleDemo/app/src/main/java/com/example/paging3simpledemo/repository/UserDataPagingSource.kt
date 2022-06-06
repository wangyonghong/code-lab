package com.example.paging3simpledemo.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3simpledemo.common.entity.ResponseEntity
import com.example.paging3simpledemo.common.util.NetworkUtil

class UserDataPagingSource : PagingSource<Int, ResponseEntity.SingleUserData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseEntity.SingleUserData> {
        return try {
            val currentPage = params.key ?: 1
            val userData = NetworkUtil.instance?.getUserData(currentPage, 3)
            val nextPage = if (currentPage < userData?.total_pages ?: 0) {
                currentPage + 1
            } else {
                null
            }
            LoadResult.Page(
                prevKey = null,
                nextKey = nextPage,
                data = userData?.data as List<ResponseEntity.SingleUserData>
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResponseEntity.SingleUserData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}