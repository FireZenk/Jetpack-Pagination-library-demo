package org.firezenk.paginationdemo.ui

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import org.firezenk.paginationdemo.domain.DataFactory
import java.util.concurrent.Executors

class MainViewModel(dataFactory: DataFactory = DataFactory()) : ViewModel() {

    companion object {
        private const val THREADS = 5
        private const val SIZE_HINT = 10
        private const val PAGE_SIZE = 20
    }

    private val executor = Executors.newFixedThreadPool(THREADS)

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(SIZE_HINT)
        .setPageSize(PAGE_SIZE)
        .build()

    val liveData = LivePagedListBuilder(dataFactory, pagedListConfig)
        .setFetchExecutor(executor)
        .build()
}