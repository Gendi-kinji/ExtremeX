package com.example.extremex

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.extremex.data.Destination
import com.example.extremex.data.OfflineRepository
import com.example.extremex.data.User
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData

class DestinationViewModel(
    private val repository: OfflineRepository
) : ViewModel() {

    // LiveData for destinations
    val allDestinations: LiveData<List<Destination>> = repository.getAllDestinations().asLiveData()

    // LiveData for users
    val allUsers: LiveData<List<User>> = repository.getAllUsers().asLiveData()

    // Destination operations
    fun insert(destination: Destination) = viewModelScope.launch {
        repository.insert(destination)
    }

    fun update(destination: Destination) = viewModelScope.launch {
        repository.update(destination)
    }

    fun delete(destination: Destination) = viewModelScope.launch {
        repository.delete(destination)
    }

    fun getDestinationById(id: Int): LiveData<Destination> {
        return repository.getDestination(id).asLiveData()
    }

    fun getDestinationByName(name: String): LiveData<Destination> {
        return repository.getDestination(name).asLiveData()
    }

    // User operations
    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        repository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repository.deleteUser(user)
    }

    fun getUserByName(name: String): LiveData<User> {
        return repository.getUser(name).asLiveData()
    }
}
