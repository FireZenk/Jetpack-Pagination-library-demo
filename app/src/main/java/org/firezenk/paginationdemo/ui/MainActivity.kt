package org.firezenk.paginationdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import org.firezenk.paginationdemo.R
import org.firezenk.paginationdemo.databinding.MainActivityBinding
import org.firezenk.paginationdemo.domain.PokemonModel

typealias ObserveResults = Observer<PagedList<PokemonModel>>

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.liveData.observe(this, ObserveResults {
            adapter.submitList(it)
        })

        binding.list.adapter = adapter
    }
}
