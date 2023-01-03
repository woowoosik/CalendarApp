package com.example.mycalendar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.mycalendar.EventObserver
import com.example.mycalendar.R
import com.example.mycalendar.databinding.FragmentUpdateBinding
import com.example.mycalendar.viewmodel.MainViewModel


class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding

    private lateinit var mainViewModel : MainViewModel
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(activity as ViewModelStoreOwner)[MainViewModel::class.java]
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
/*
        mainViewModel.tf.observe(viewLifecycleOwner, Observer{
            Log.e("UpdataFragment"," 누름 $it")
            requireActivity().supportFragmentManager.beginTransaction()
                .remove(UpdateFragment()).commit()

            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction().remove(this@UpdateFragment).commit()
            fragmentManager.popBackStack()
        })*/


        mainViewModel.updateComplate.observe(viewLifecycleOwner, EventObserver {
            Log.e("update observer","")
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .remove(this@UpdateFragment)
                .commit()
            fragmentManager.popBackStack()
        })
    }
}