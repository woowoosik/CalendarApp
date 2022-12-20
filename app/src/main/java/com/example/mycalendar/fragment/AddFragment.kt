package com.example.mycalendar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.mycalendar.R
import com.example.mycalendar.databinding.FragmentAddBinding
import com.example.mycalendar.databinding.FragmentDetailBinding
import com.example.mycalendar.viewmodel.MainViewModel

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding

    private lateinit var mainViewModel : MainViewModel
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(activity as ViewModelStoreOwner)[MainViewModel::class.java]
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this


    }
}