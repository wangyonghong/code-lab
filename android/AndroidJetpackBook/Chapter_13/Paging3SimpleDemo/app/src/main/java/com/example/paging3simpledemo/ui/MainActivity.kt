package com.example.paging3simpledemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3simpledemo.R
import com.example.paging3simpledemo.common.entity.ResponseEntity
import com.example.paging3simpledemo.view_model.ViewModelActivityMain
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var pageSingleUserData: PagingData<ResponseEntity.SingleUserData>
    lateinit var viewModel: ViewModelActivityMain
    lateinit var demoRv: RecyclerView
    lateinit var rvAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = LinearLayoutManager(this)
        demoRv = findViewById(R.id.activity_main_rv)
        demoRv.layoutManager = manager
        rvAdapter = RecyclerViewAdapter()
        demoRv.adapter = rvAdapter
        viewModel = ViewModelActivityMain()
        lifecycleScope.launch {
            viewModel.getUserData().collectLatest {
                pageSingleUserData = it
                rvAdapter.submitData(it)
            }
        }
    }
}