package com.example.extremex
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.extremex.DestinationViewModel
import com.example.extremex.data.OfflineRepository



class DestinationViewModelFactory(
    private val repository: OfflineRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DestinationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DestinationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
