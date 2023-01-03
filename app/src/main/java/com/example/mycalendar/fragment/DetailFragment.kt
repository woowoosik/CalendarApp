package com.example.mycalendar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.mycalendar.R
import com.example.mycalendar.databinding.ActivityDetailPageBinding
import com.example.mycalendar.databinding.FragmentDetailBinding
import com.example.mycalendar.viewmodel.MainViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private lateinit var mainViewModel : MainViewModel
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(activity as ViewModelStoreOwner)[MainViewModel::class.java]
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        binding.bt1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, UpdateFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}