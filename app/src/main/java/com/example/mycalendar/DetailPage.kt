package com.example.mycalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.mycalendar.databinding.ActivityDetailPageBinding
import com.example.mycalendar.viewmodel.MainViewModel

class DetailPage : AppCompatActivity() {
    private lateinit var binding : ActivityDetailPageBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_page)

        viewModel = ViewModelProvider(this,MainViewModel.Factory(application))[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
/*

        Log.e("DetailPage", " 1${viewModel.itemData.value}")
        viewModel.itemData.observe(this, Observer {
            Log.e("DetailPage", " 2${it.title}")
            binding.tv1.text = it.date
            binding.tv2.text = it.title
            binding.tv3.text = it.content
        })
*/


       /*
        12.8 주석
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
*/
/*
        DataBindingUtil.setContentView<ActivityDetailPageBinding>(this, R.layout.activity_detail_page).apply {
            lifecycleOwner = this@DetailPage
            viewModel = viewModel
        }*/


    }
}