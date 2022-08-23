package com.zavosh.jetpackpaggin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.jetpackpaggin.adapter.LoaderStateAdapter
import com.zavosh.jetpackpaggin.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            mAdapter = RecyclerViewAdapter()
            val loadStateAdapter = LoaderStateAdapter {
                mAdapter.retry()
            }
            adapter = mAdapter.withLoadStateFooter(loadStateAdapter)
        }
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        lifecycleScope.launchWhenCreated {
            mViewModel.getList().collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

}