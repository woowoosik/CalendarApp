package com.example.mycalendar

import androidx.lifecycle.Observer
import com.example.mycalendar.viewmodel.MainViewModel

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<MainViewModel.Event<T>> {
    override fun onChanged(event: MainViewModel.Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}