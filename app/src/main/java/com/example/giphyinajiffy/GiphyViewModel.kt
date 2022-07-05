package com.example.giphyinajiffy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GiphyViewModel : ViewModel() {
    private val _actionBarTitle = MutableLiveData<String>()
    val actionBarTitle: LiveData<String> get() = _actionBarTitle

    /**
     * Update the action bar title of the app to something other than the Fragment
     * label. Such as a GIF title.
     */
    fun updateActionBarTitle(title: String) {
        _actionBarTitle.value = title
    }
}
